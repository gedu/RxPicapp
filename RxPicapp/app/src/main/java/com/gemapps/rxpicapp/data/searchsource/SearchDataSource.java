package com.gemapps.rxpicapp.data.searchsource;

import com.gemapps.rxpicapp.model.Picture;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;

/**
 * Created by edu on 5/15/17.
 */

public interface SearchDataSource {

    ConnectableObservable<List<Picture>> getPicturesFromQuery(int page, String query);
}
