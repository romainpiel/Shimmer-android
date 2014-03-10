package com.romainpiel.shimmer;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Shimmer
 * User: romainpiel
 * Date: 06/03/2014
 * Time: 10:19
 */
public class ShimmerTextView extends TextView {

    private static final int DEFAULT_REFLECTION_COLOR = 0xFFFFFFFF;

    // center position of the gradient
    private float gradientX;

    // shader applied on the text view
    // only null until the first global layout
    private LinearGradient linearGradient;

    // shader's local matrix
    // never null
    private Matrix linearGradientMatrix;

    // shimmer reflection color
    private int reflectionColor;

    // true when animating
    private boolean isShimmering;

    // true after first global layout
    private boolean isSetUp;

    // callback called after first global layout
    private AnimationSetupCallback callback;

    public interface AnimationSetupCallback {
        void onSetupAnimation(ShimmerTextView shimmerTextView);
    }

    public ShimmerTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ShimmerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShimmerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public float getGradientX() {
        return gradientX;
    }

    protected void setGradientX(float gradientX) {
        this.gradientX = gradientX;
        invalidate();
    }

    public boolean isShimmering() {
        return isShimmering;
    }

    protected void setShimmering(boolean isShimmering) {
        this.isShimmering = isShimmering;
    }

    public boolean isSetUp() {
        return isSetUp;
    }

    public void setSetUp(boolean isSetUp) {
        this.isSetUp = isSetUp;
    }

    public void setAnimationSetupCallback(AnimationSetupCallback callback) {
        this.callback = callback;
    }

    public int getReflectionColor() {
        return reflectionColor;
    }

    public void setReflectionColor(int reflectionColor) {
        this.reflectionColor = reflectionColor;
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    private void init(Context context, AttributeSet attributeSet) {

        reflectionColor = DEFAULT_REFLECTION_COLOR;

        if (attributeSet != null) {
            TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerTextView, 0, 0);
            if (a != null) {
                try {
                    reflectionColor = a.getColor(R.styleable.ShimmerTextView_reflectionColor, DEFAULT_REFLECTION_COLOR);
                } catch (Exception e) {
                    android.util.Log.e("ShimmerTextView", "Error while creating the view:", e);
                } finally {
                    a.recycle();
                }
            }
        }

        linearGradientMatrix = new Matrix();
    }

    private void resetLinearGradient() {

        int textColor = getCurrentTextColor();

        // our gradient is a simple linear gradient from textColor to reflectionColor. its axis is at the center
        // when it's outside of the view, the outer color (textColor) will be repeated (Shader.TileMode.CLAMP)
        // initially, the linear gradient is positioned on the left side of the view
        linearGradient = new LinearGradient(- getWidth(), 0, 0, 0,
                new int[]{
                        textColor,
                        reflectionColor,
                        textColor,
                },
                new float[]{
                        0,
                        0.5f,
                        1
                },
                Shader.TileMode.CLAMP);

        getPaint().setShader(linearGradient);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation")
                @Override
                public void onGlobalLayout() {

                    resetLinearGradient();

                    isSetUp = true;

                    if (callback != null) {
                        callback.onSetupAnimation(ShimmerTextView.this);
                    }

                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // only draw the shader gradient over the text while animating
        if (isShimmering) {

            // first onDraw() when shimmering
            if (getPaint().getShader() == null) {
                getPaint().setShader(linearGradient);
            }

            // translate the shader local matrix
            linearGradientMatrix.setTranslate(2 * gradientX, 0);

            // this is required in order to invalidate the shader's position
            linearGradient.setLocalMatrix(linearGradientMatrix);

        } else {
            // we're not animating, remove the shader from the paint
            getPaint().setShader(null);
        }

        super.onDraw(canvas);
    }
}
