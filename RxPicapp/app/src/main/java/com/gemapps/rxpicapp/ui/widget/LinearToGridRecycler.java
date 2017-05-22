package com.gemapps.rxpicapp.ui.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.recycleradapter.BaseRecyclerViewAdapter;

import java.util.List;

import butterknife.BindInt;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by edu on 5/11/17.
 */

public class LinearToGridRecycler extends RecyclerView {

    private static final String TAG = "LinearToGridRecycler";
    private LinearLayoutManager mLinearManager;
    private GridLayoutManager mGridManager;

    private BaseRecyclerViewAdapter mAdapter;
    private boolean mIsLoadingMore = false;
    private boolean mIsLinearLayout = true;
    private Observable<Object> mLoadMore;
    private ObservableEmitter<Object> mLoadMoreEmitter;

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
        mLoadMore = Observable.create(e -> {
            Log.d(TAG, "init: COMPLETABLE");
            mLoadMoreEmitter = e;
        }).observeOn(AndroidSchedulers.mainThread());
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

    public void swapListStyle(){
        mIsLinearLayout = !mIsLinearLayout;
        setLayoutManager();
        mAdapter.notifyDataSetChanged();
    }

    public boolean isLinearLayout(){
        return mIsLinearLayout;
    }

    public Observable<Object> getLoadingMoreObserver() {
        return mLoadMore;
    }

    public void loadingMoreFinished() {
        if(mIsLoadingMore) {
            mIsLoadingMore = false;
            mAdapter.removeBottomProgress();
        }
    }

    public boolean isLoadingMore() {
        Log.d(TAG, "Asking for is loading more: "+mIsLoadingMore);
        return mIsLoadingMore;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        RecyclerSavedState savedState = new RecyclerSavedState(super.onSaveInstanceState());
        savedState.isLinear = mIsLinearLayout;
        savedState.isLoading = mIsLoadingMore;
        savedState.currentPictures = mAdapter.getItems();
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof RecyclerSavedState) {
            RecyclerSavedState savedState = (RecyclerSavedState) state;
            super.onRestoreInstanceState(savedState.getSuperState());
            restoreItems(savedState);
            restoreLoading(savedState);
            restoreLayout(savedState);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    private void restoreItems(RecyclerSavedState savedState) {
        mAdapter.addItems(savedState.currentPictures);
    }

    private void restoreLoading(RecyclerSavedState savedState) {
        mIsLoadingMore = savedState.isLoading;
        Log.d(TAG, "restore loading more data: "+mIsLoadingMore);
    }

    private void restoreLayout(RecyclerSavedState savedState) {
        mIsLinearLayout = savedState.isLinear;
        setLayoutManager();
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


//                    mHandler.post(mAddProgressRunnable);
                    if(mLoadMoreEmitter != null){
                        mIsLoadingMore = true;
                        mLoadMoreEmitter.onNext(new Object());
                    }else {
                        Log.d(TAG, "null");
                    }
                }
            }
        }
    };

    private int getLastVisibleItem() {
        if (hasLinearManager()) return getLinearManager().findLastVisibleItemPosition();
        else return getGridManager().findLastVisibleItemPosition();
    }

    private boolean hasLinearManager() {
        return getLayoutManager() instanceof LinearLayoutManager;
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

    public static class RecyclerSavedState extends BaseSavedState {

        List<Picture> currentPictures;
        boolean isLoading;
        boolean isLinear;

        public RecyclerSavedState(Parcel source) {
            super(source);
            source.readTypedList(currentPictures, Picture.CREATOR);
            isLoading = source.readInt() == 1;
            isLinear = source.readInt() == 1;
        }

        public RecyclerSavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeTypedList(currentPictures);
            out.writeInt(isLoading ? 1 : 0);
            out.writeInt(isLinear ? 1 : 0);
        }

        public static final Creator<RecyclerSavedState> CREATOR = new Creator<RecyclerSavedState>() {
            public RecyclerSavedState createFromParcel(Parcel in) {
                return new RecyclerSavedState(in);
            }

            public RecyclerSavedState[] newArray(int size) {
                return new RecyclerSavedState[size];
            }
        };
    }
}
