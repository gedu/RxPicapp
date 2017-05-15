package com.gemapps.rxpicapp.networking.rest.search;

import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.networking.rest.FlickrBase;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by edu on 5/4/17.
 */

public interface FlickrSearchService {

    @GET(FlickrBase.GET_RECENT_METHOD)
    Observable<PictureDeserializer.ResultValue> searchRecentPhotos(
            @QueryMap(encoded = true)Map<String, String> options
    );

    @GET(FlickrBase.PEOPLE_PROFILE_METHOD)
    Observable<PictureDeserializer.ResultValue> searchPeopleProfile(
            @QueryMap Map<String, String> options
    );
}
