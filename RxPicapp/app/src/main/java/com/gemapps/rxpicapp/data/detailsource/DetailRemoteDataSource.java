package com.gemapps.rxpicapp.data.detailsource;

import com.gemapps.rxpicapp.model.Comment;
import com.gemapps.rxpicapp.networking.deserializer.CommentDeserializer;
import com.gemapps.rxpicapp.networking.deserializer.FlickrCommentFactory;
import com.gemapps.rxpicapp.networking.rest.FlickrService;
import com.gemapps.rxpicapp.networking.rest.PicappService;
import com.gemapps.rxpicapp.networking.rest.RetrofitAdapter;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by edu on 5/16/17.
 */

public class DetailRemoteDataSource implements DetailDataSource {

    private PicappService mService;

    public DetailRemoteDataSource() {
        mService = new PicappService.Builder()
                .setFactory(FlickrCommentFactory.create())
                .build();
    }

    @Override
    public Observable<List<Comment>> getComments(String photoId) {
        FlickrService flickrService = mService.createService(FlickrService.class);
        return flickrService.getComments(RetrofitAdapter.buildCommentOptions(photoId))
                .map(CommentDeserializer.ResultValue::getComments);

    }
}
