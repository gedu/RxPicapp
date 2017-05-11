package com.gemapps.rxpicapp.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.gemapps.rxpicapp.R;

/**
 * Created by edu on 5/11/17.
 */

public class MenuUtil {

    public static Drawable getDrawable(Context context, boolean isList) {
        return ContextCompat.getDrawable(context, isList ?
                R.drawable.vc_list_drawable : R.drawable.vc_grid_drawable);
    }
}
