package com.gemapps.rxpicapp.ui.butter;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.recycleradapter.HomePictureViewAdapter;
import com.gemapps.rxpicapp.ui.widget.LinearToGridRecycler;
import com.gemapps.rxpicapp.util.MenuUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by edu on 5/15/17.
 */

public abstract class PictureLoadMoreListFragment extends ButterFragment {

    private static final String TAG = "PictureLoadMoreListFrag";
    @BindView(R.id.progress_bar)
    protected ProgressBar mProgressBar;

    @BindView(R.id.home_picture_recycler)
    protected LinearToGridRecycler mPictureRecycler;

    protected HomePictureViewAdapter mAdapter;

    private HomePictureViewAdapter.PictureAdapterListener mListener = this::onClickPicture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View rootView = onCreateView(inflater, container, getLayoutResource());
        setupRecyclerView();
        return rootView;
    }

    private void setupRecyclerView() {
        mAdapter = new HomePictureViewAdapter(getActivity(), new ArrayList<>(), mListener);
        mPictureRecycler.addAdapter(mAdapter);
        mPictureRecycler.getLoadingMoreObserver()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onLoadMoreSubscriber());
    }

    private DisposableObserver<Object> onLoadMoreSubscriber() {
        return new DisposableObserver<Object>() {
            @Override
            public void onNext(Object value) {
                onLoadMore();
            }
            @Override
            public void onComplete() {}
            @Override
            public void onError(Throwable e) {
                onLoadMoreError();
            }
        };
    }

    protected void swapPicturesListView(MenuItem item) {
        mPictureRecycler.swapListStyle();
        item.setIcon(MenuUtil.getDrawable(getContext(),
                mPictureRecycler.isLinearLayout()));
    }

    protected void clear() {
        mAdapter.clear();
    }

    protected void addItems(List<Picture> pictures) {
        mPictureRecycler.loadingMoreFinished();
        mAdapter.addPictures(pictures);
    }

    protected abstract @LayoutRes int getLayoutResource();
    protected void onLoadMore() {
        mAdapter.addBottomProgress();
    }
    protected abstract void onLoadMoreError();
    protected abstract void onClickPicture(Picture picture);
}
