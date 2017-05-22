package com.gemapps.rxpicapp.ui.recycleradapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;

import java.util.List;

/**
 * Created by edu on 5/11/17.
 */

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T>
        extends RecyclerView.Adapter<VH> {

    public static final int VIEW_LOADING_TYPE = 0;

    protected ButterViewHolder onCreateBottomProgressBar(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.progress_item_list, parent, false);
        return new BottomProgressHolder(v);
    }

    protected boolean isBottomProgressHolder(ButterViewHolder holder) {
        return (holder instanceof BottomProgressHolder);
    }

    protected boolean isBottomProgressType(int type) {
        return type == VIEW_LOADING_TYPE;
    }

    public abstract void addBottomProgress();

    public abstract void removeBottomProgress();

    public abstract List<T> getItems();

    public abstract void addItems(List<T> items);

    public static class BottomProgressHolder extends ButterViewHolder {

        private BottomProgressHolder(View root) {
            super(root);
        }
    }
}
