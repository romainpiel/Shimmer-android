package com.romainpiel.shimmer.sample;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class MainActivity extends Activity {

    ShimmerTextView tv;
    Animator shimmerAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
    }

    public void toggleAnimation(View target) {
        if (shimmerAnimator != null) {
            shimmerAnimator.cancel();
        } else {
            Shimmer.animate(tv, new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(final Animator animation) {
                    shimmerAnimator = animation;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    shimmerAnimator = null;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }
}
