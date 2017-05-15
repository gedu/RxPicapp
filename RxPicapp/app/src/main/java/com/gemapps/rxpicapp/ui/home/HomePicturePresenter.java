package com.gemapps.rxpicapp.ui.home;

import android.util.Log;

import com.gemapps.rxpicapp.data.homesource.HomePictureRepository;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.util.PicturePager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;

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
    public void unSubscribe() {
        if(mSubscription != null) mSubscription.dispose();
    }

    @Override
    public void subscribe() {
        mView.showProgressBar();
        loadPictures();
    }

    @Override
    public void loadPictures(){

        unSubscribe();
        mPager.stopPagination();

        ConnectableObservable<List<Picture>> connectible = mRepository.getPictures(mPager.getCurrentPage());
        mPager.startPagination(connectible);
        mSubscription = connectible
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(listenForPictures());

        connectible.connect();
    }

    private DisposableObserver<List<Picture>> listenForPictures() {
        return new DisposableObserver<List<Picture>>() {
            @Override
            public void onNext(List<Picture> pictures) {
                Log.d(TAG, "PRESENTER");
                mView.addPictures(pictures);
                mView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };
    }
}
