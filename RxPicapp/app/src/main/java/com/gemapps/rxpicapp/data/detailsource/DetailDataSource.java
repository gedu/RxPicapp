package com.gemapps.rxpicapp.data.detailsource;

import com.gemapps.rxpicapp.model.Comment;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by edu on 5/16/17.
 */

public interface DetailDataSource {

    Observable<List<Comment>> getComments(String photoId);
}
