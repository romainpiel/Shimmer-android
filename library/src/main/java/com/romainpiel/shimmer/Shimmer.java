package com.romainpiel.shimmer;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

/**
 * Shimmer
 * User: romainpiel
 * Date: 06/03/2014
 * Time: 15:42
 */
public class Shimmer {

    public static void animate(final ShimmerTextView shimmerTextView, final Animator.AnimatorListener animatorListener) {
        shimmerTextView.setShimmering(true);

        final Runnable animate = new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(shimmerTextView, "maskX", 0, shimmerTextView.getWidth());
                if (shimmerTextView.getShimmerDirection() == ShimmerTextView.RIGHT_TO_LEFT) {
                    animator.setFloatValues(shimmerTextView.getWidth(), 0);
                }
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setDuration(1000);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        shimmerTextView.setShimmering(false);
                        shimmerTextView.postInvalidate();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                if (animatorListener != null) {
                    animator.addListener(animatorListener);
                }
                animator.start();
            }
        };

        if (!shimmerTextView.isSetUp()) {
            shimmerTextView.setAnimationSetupCallback(new ShimmerTextView.AnimationSetupCallback() {
                @Override
                public void onSetupAnimation(final ShimmerTextView shimmerTextView) {
                    animate.run();
                }
            });
        } else {
            animate.run();
        }
    }
}
