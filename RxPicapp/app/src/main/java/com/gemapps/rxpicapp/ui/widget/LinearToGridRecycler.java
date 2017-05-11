package com.gemapps.rxpicapp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.gemapps.rxpicapp.R;

import butterknife.BindInt;
import butterknife.ButterKnife;

/**
 * Created by edu on 5/11/17.
 */

public class LinearToGridRecycler extends RecyclerView {

    private LinearLayoutManager mLinearManager;
    private GridLayoutManager mGridManager;

    private BaseRecyclerViewAdapter mAdapter;
    private boolean mIsLoadingMore = false;
    private boolean mIsLinearLayout = true;

    @BindInt(R.integer.grid_column_count) int COLUMN_COUNT;

    public LinearToGridRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LinearToGridRecycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        ButterKnife.bind(this, this);
        setupLayoutManagers(context);
        setLayoutManager();
        addOnScrollListener(SCROLL_BOTTOM_LISTENER);
    }

    private void setupLayoutManagers(Context context) {
        mLinearManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        mGridManager = new GridLayoutManager(context, COLUMN_COUNT,
                LinearLayoutManager.VERTICAL, false);
        mGridManager.setSpanSizeLookup(SPAN_LOOKUP);
    }

    private void setLayoutManager() {
        setLayoutManager(mIsLinearLayout ? mLinearManager : mGridManager);
    }

    public void addAdapter(BaseRecyclerViewAdapter adapter) {
        setAdapter(adapter);
        mAdapter = adapter;
    }

    private final RecyclerView.OnScrollListener SCROLL_BOTTOM_LISTENER = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0) {
                int totalItems = getLayoutManager().getItemCount();
                int lastVisibleItem = getLastVisibleItem();

                //note: +3 how many of mItems to have below the current scroll position before loading more
                if (!mIsLoadingMore && totalItems <= (lastVisibleItem + 3)) {

                    mIsLoadingMore = true;
//                    mHandler.post(mAddProgressRunnable);
                }
            }
        }
    };

    private int getLastVisibleItem() {
        if (mIsLinearLayout) return getLinearManager().findLastVisibleItemPosition();
        else return getGridManager().findLastVisibleItemPosition();
    }

    private LinearLayoutManager getLinearManager() {
        return ((LinearLayoutManager) getLayoutManager());
    }

    private GridLayoutManager getGridManager() {
        return ((GridLayoutManager) getLayoutManager());
    }

    private final GridLayoutManager.SpanSizeLookup SPAN_LOOKUP = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {

            return mIsLoadingMore &&
                    mAdapter.getItemViewType(position) == BaseRecyclerViewAdapter.VIEW_LOADING_TYPE ?
                    mGridManager.getSpanCount() : 1;
        }
    };
}
