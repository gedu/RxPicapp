package com.gemapps.rxpicapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.gemapps.rxpicapp.data.searchsource.SearchRepository;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.PresenterSubscriptionStorage;
import com.gemapps.rxpicapp.ui.detail.DetailActivity;
import com.gemapps.rxpicapp.util.pager.PicturePager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by edu on 5/14/17.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private static final String TAG = "SearchPresenter";

    private SearchRepository mRepository;
    private SearchContract.View mView;
    private Disposable mSubscriber;
    private PicturePager mPager;
    private String mCurrentQuery;

    public SearchPresenter(SearchRepository repository,
                           SearchContract.View view,
                           PicturePager pager) {
        mRepository = repository;
        mView = view;
        mPager = pager;

        mView.setPresenter(this);
    }

    @Override
    public void load() {

    }

    @Override
    public void onViewCreated(Bundle savedState) {

        if(savedState == null) mView.setupStarUpUI();
        else loadMoreIfNeeded();
    }

    @Override
    public void onResume() {
        loadMoreIfNeeded();
    }

    private void loadMoreIfNeeded() {
        if(mView.isLoadingMore()) loadPreviousPictures();
    }

    private void loadPreviousPictures() {
        loadMore();
    }

    @Override
    public void dispose() {
        clearSubscription();
    }

    @Override
    public void loadMore() {
        searchFor(mCurrentQuery);
    }

    @Override
    public void onClickPicture(Picture picture) {
        Intent intent = DetailActivity.newInstance(mView.getContext(), picture);
        mView.showPictureDetail(intent);
    }

    @Override
    public void searchFor(String query) {
        mCurrentQuery = query;

        ConnectableObservable<List<Picture>> connectible = getPicturesObservable();
        mPager.startPagination(connectible);
        mSubscriber = connectible
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(listenForPictures());
        connectible.connect();
    }

    private DisposableObserver<List<Picture>> listenForPictures() {
        return new DisposableObserver<List<Picture>>(){

            @Override
            public void onNext(List<Picture> pictures) {
                Log.d(TAG, "onNext() called with: pictures = <" + pictures.size() + ">");
                PresenterSubscriptionStorage.getInstance().removeSubscription(SearchPresenter.class);
                mView.hideProgressBar();
                mView.addPictures(pictures);
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };
    }

    private ConnectableObservable<List<Picture>> getPicturesObservable() {
        ConnectableObservable<List<Picture>> connectible = PresenterSubscriptionStorage
                .getInstance().getSubscription(SearchPresenter.class);
        if(connectible != null) return connectible;

        connectible = mRepository.getPicturesFromQuery(mPager.getCurrentPage(), mCurrentQuery);
        PresenterSubscriptionStorage.getInstance().saveSubscription(connectible,
                SearchPresenter.class);
        return connectible;
    }

    private void clearSubscription() {
        if(mSubscriber != null) mSubscriber.dispose();
        mPager.stopPagination();
    }
}
