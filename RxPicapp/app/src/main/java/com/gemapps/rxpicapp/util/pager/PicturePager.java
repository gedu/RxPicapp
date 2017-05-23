package com.gemapps.rxpicapp.util.pager;

import android.util.Log;

import com.gemapps.rxpicapp.model.Picture;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by edu on 5/12/17.
 */

public class PicturePager {
    private static final String TAG = "PicturePager";
    private int mCurrentPage;
    private Disposable mObserver;

    public PicturePager() {
        mCurrentPage = 1;
    }

    public void startPagination(ConnectableObservable<List<Picture>> connectible) {
        mObserver = connectible
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(updatePage());
    }

    private DisposableObserver<List<Picture>> updatePage() {
        return new DisposableObserver<List<Picture>>() {
            @Override
            public void onNext(List<Picture> value) {
                Log.d(TAG, "PAGER");
                mCurrentPage++;
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
    }

    public void stopPagination() {
        if(mObserver != null) mObserver.dispose();
    }

    public void goBackPage() {
        --mCurrentPage;
    }
    public int getCurrentPage() {
        return mCurrentPage;
    }
}
