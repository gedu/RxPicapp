package com.gemapps.rxpicapp.ui.butter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by edu on 4/27/17.
 */

public class ButterViewHolder extends RecyclerView.ViewHolder {
    public ButterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
