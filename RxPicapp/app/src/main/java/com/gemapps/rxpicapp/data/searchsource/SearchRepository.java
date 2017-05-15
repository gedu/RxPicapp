package com.gemapps.rxpicapp.data.searchsource;

import com.gemapps.rxpicapp.model.Picture;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;

/**
 * Created by edu on 5/15/17.
 */

public class SearchRepository implements SearchDataSource {

    private SearchDataSource mRemoteDataSource;

    public SearchRepository(SearchDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public ConnectableObservable<List<Picture>> getPicturesFromQuery(int page, String query) {
        return mRemoteDataSource.getPicturesFromQuery(page, query);
    }
}
