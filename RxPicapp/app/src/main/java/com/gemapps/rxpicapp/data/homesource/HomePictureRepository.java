package com.gemapps.rxpicapp.data.homesource;

import com.gemapps.rxpicapp.model.Picture;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;

/**
 * Created by edu on 4/17/17.
 * Wrapper class to handle request to the flickr API and get local data (coming soon)
 */
public class HomePictureRepository implements HomePictureDataSource {

    private static final String TAG = "HomePictureRepository";
    private HomePictureDataSource mRemoteDataSource;

    public HomePictureRepository(HomePictureDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public ConnectableObservable<List<Picture>> getPictures(int page) {
        return mRemoteDataSource.getPictures(page);
    }
}
