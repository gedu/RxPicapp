package com.gemapps.rxpicapp.data.detailsource;

import com.gemapps.rxpicapp.model.Comment;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by edu on 5/16/17.
 */

public class DetailRepository implements DetailDataSource {

    private DetailDataSource mRemoteDataSource;

    public DetailRepository(DetailDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<List<Comment>> getComments() {
        return mRemoteDataSource.getComments();
    }
}
