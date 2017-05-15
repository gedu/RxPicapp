package com.gemapps.rxpicapp.data.homesource;

import com.gemapps.rxpicapp.model.Picture;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;

/**
 * Created by edu on 4/17/17.
 */

public interface HomePictureDataSource {

    ConnectableObservable<List<Picture>> getPictures(int page);
}
