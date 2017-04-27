package com.gemapps.rxpicapp.data.homesource;

import com.gemapps.rxpicapp.model.Picture;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by edu on 4/17/17.
 */

public class HomePictureRemoteDataSource implements HomePictureDataSource {

    List<Picture> mPictures;

    @Override
    public Observable<List<Picture>> getPictures() {

        return Observable.just(mPictures);
    }
}
