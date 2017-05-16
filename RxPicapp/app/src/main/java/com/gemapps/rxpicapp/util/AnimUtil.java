package com.gemapps.rxpicapp.util;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by edu on 5/16/17.
 */

public class AnimUtil {

    private static final AccelerateDecelerateInterpolator INTERPOLATOR = new AccelerateDecelerateInterpolator();

    public static ViewPropertyAnimator fadeAnimation(View view) {
        return view.animate()
                .alpha(1f)
                .setDuration(300L)
                .setInterpolator(INTERPOLATOR);
    }

    public static class VIewSearchEmpty extends SearchView {

        public VIewSearchEmpty(Context context, AttributeSet attrs) {
            super(context, attrs);

        }

    }
}
