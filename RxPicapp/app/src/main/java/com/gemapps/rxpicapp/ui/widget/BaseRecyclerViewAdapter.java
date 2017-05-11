package com.gemapps.rxpicapp.ui.widget;

import android.support.v7.widget.RecyclerView;

/**
 * Created by edu on 5/11/17.
 */

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    public static final int VIEW_LOADING_TYPE = 0;
}
