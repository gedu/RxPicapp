package com.gemapps.rxpicapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.data.homesource.HomePictureRepository;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.PresenterSubscriptionStorage;
import com.gemapps.rxpicapp.ui.detail.DetailActivity;
import com.gemapps.rxpicapp.ui.search.SearchActivity;
import com.gemapps.rxpicapp.util.ConnectionUtil;
import com.gemapps.rxpicapp.util.pager.PicturePager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by edu on 4/12/17.
 */

public class HomePicturePresenter implements HomePictureContract.Presenter {

    private static final String TAG = "HomePicturePresenter";
    private HomePictureContract.View mView;
    private HomePictureRepository mRepository;
    private PicturePager mPager;
    private Disposable mSubscription;

    public HomePicturePresenter(HomePictureContract.View homeView,
                                HomePictureRepository homePictureRepository,
                                PicturePager pager) {
        mView = homeView;
        mRepository = homePictureRepository;
        mPager = pager;
        mView.setPresenter(this);
    }

    @Override
    public void dispose() {
        if(mSubscription != null) {
            mSubscription.dispose();
        }
        mPager.stopPagination();
    }

    @Override
    public void load() {
        mView.showProgressBar();
        loadPictures();
    }

    @Override
    public void onViewCreated(Bundle savedState) {
        if(!hasConnection()) {
            mView.hideProgress();
            mView.showConnectionError();
            return;
        }
        if(savedState == null) load();
    }

    @Override
    public void onResume() {
        if(mView.isLoadingMore()) loadPictures();
    }

    @Override
    public void loadPictures() {

        ConnectableObservable<List<Picture>> connectible = getPicturesObservable();
        mPager.startPagination(connectible);
        mSubscription = connectible
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(listenForPictures());

        connectible.connect();
    }

    private ConnectableObservable<List<Picture>> getPicturesObservable() {
        ConnectableObservable<List<Picture>> connectible = PresenterSubscriptionStorage
                .getInstance().getSubscription(HomePicturePresenter.class);
        if(connectible != null) return connectible;

        connectible = mRepository.getPictures(mPager.getCurrentPage());
        PresenterSubscriptionStorage.getInstance().saveSubscription(connectible,
                HomePicturePresenter.class);
        return connectible;
    }

    @Override
    public void onClickPicture(Picture picture) {
        Intent intent = DetailActivity.newInstance(mView.getContext(), picture);
        mView.showPictureDetail(intent);
    }

    @Override
    public boolean optionSelected(MenuItem item) {
        switch (item.getItemId())  {
            case R.id.action_swap_list:
                mView.swapPicturesListView(item);
                return true;
            case R.id.action_search:
                mView.startSearch(SearchActivity.newInstance(mView.getContext(),
                        mView.isLinearLayout()));
                return true;
            default:
                return false;
        }
    }

    private boolean hasConnection() {
        return ConnectionUtil.getInstance(mView.getContext()).hasConnection();
    }

    private DisposableObserver<List<Picture>> listenForPictures() {
        return new DisposableObserver<List<Picture>>() {
            @Override
            public void onNext(List<Picture> pictures) {
                Log.d(TAG, "PRESENTER: "+pictures.size());
                PresenterSubscriptionStorage.getInstance().removeSubscription(HomePicturePresenter.class);
                mView.addPictures(pictures);
                mView.hideProgress();
                mView.hideErrorView();
            }

            @Override
            public void onError(Throwable e) {
                mView.showPictureError();
            }

            @Override
            public void onComplete() {}
        };
    }
}
