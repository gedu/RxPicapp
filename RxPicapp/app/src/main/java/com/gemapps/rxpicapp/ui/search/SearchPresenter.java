package com.gemapps.rxpicapp.ui.search;

import android.util.Log;

import com.gemapps.rxpicapp.data.searchsource.SearchRepository;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.util.PicturePager;

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

    public SearchPresenter(SearchRepository repository,
                           SearchContract.View view,
                           PicturePager pager) {
        mRepository = repository;
        mView = view;
        mPager = pager;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        clearSubscription();
    }

    @Override
    public void searchFor(String query) {
        Log.d(TAG, "searchFor() called with: query = <" + query + ">");
        clearSubscription();
        mView.showProgressBar();
        ConnectableObservable<List<Picture>> connectible = mRepository.getPicturesFromQuery(mPager.getCurrentPage(), query);
        mPager.startPagination(connectible);
        mSubscriber = connectible
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Picture>>(){

                    @Override
                    public void onNext(List<Picture> pictures) {
                        Log.d(TAG, "onNext() called with: pictures = <" + pictures.size() + ">");
                        mView.hideProgressBar();
                        mView.addPictures(pictures);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        connectible.connect();
    }

    private void clearSubscription() {
        if(mSubscriber != null) mSubscriber.dispose();
        mPager.stopPagination();
    }
}