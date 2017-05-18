package com.gemapps.rxpicapp.networking.rest;

import com.gemapps.rxpicapp.model.Author;
import com.gemapps.rxpicapp.networking.deserializer.CommentDeserializer;
import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.networking.deserializer.ResultHolder;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by edu on 5/4/17.
 */

public interface FlickrService {

    @GET(FlickrBase.GET_RECENT_METHOD)
    Observable<ResultHolder> getRecentPhotos(
            @QueryMap(encoded = true)Map<String, String> options
    );

    @GET(FlickrBase.SEARCH_METHOD)
    Observable<PictureDeserializer.ResultValue> searchPhotos(
            @QueryMap(encoded = true)Map<String, String> options
    );

    @GET(FlickrBase.PEOPLE_PROFILE_METHOD)
    Observable<PictureDeserializer.ResultValue> searchPeopleProfile(
            @QueryMap Map<String, String> options
    );

    @GET(FlickrBase.GET_COMMENTS_METHOD)
    Observable<CommentDeserializer.ResultValue> getComments(
            @QueryMap Map<String, String> options
    );

    @GET(FlickrBase.GET_AUTHOR_INFO)
    Observable<Author> getAuthorInfo(
            @QueryMap Map<String, String> options
    );
}
