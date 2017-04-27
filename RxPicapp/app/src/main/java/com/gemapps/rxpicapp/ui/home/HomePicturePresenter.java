package com.gemapps.rxpicapp.ui.home;

import com.gemapps.rxpicapp.data.homesource.HomePictureRepository;
import com.gemapps.rxpicapp.model.Picture;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by edu on 4/12/17.
 */

public class HomePicturePresenter implements HomePictureContract.Presenter {

    private static final String TAG = "HomePicturePresenter";
    private HomePictureContract.View mView;
    private HomePictureRepository mRepository;
    private CompositeDisposable mSubscriptions;

    public HomePicturePresenter(HomePictureContract.View homeView,
                                HomePictureRepository homePictureRepository) {
        mView = homeView;
        mRepository = homePictureRepository;
        mSubscriptions = new CompositeDisposable();
        mView.setPresenter(this);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void subscribe() {
        mView.showProgressBar();
        loadPictures();
    }

    @Override
    public void loadPictures(){
        mSubscriptions.clear();
        Disposable subscription = mRepository.getPictures()
                .subscribeWith(listenForPictures());

        mSubscriptions.add(subscription);
    }

    private DisposableObserver<List<Picture>> listenForPictures() {
        return new DisposableObserver<List<Picture>>() {
            @Override
            public void onNext(List<Picture> pictures) {
                
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
