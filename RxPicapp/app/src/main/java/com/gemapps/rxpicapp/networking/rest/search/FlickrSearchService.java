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

//    @GET("services/rest?sort=relevance&parse_tags=1&content_type=7&api_key="+ BuildConfig.FLICKR_API_KEY+"&per_page=10&page=1&" +
//            "extras=owner,date_taken,count_comments,count_faves,owner_name,url_n,url_c,url_b" +
//            "&format=json&nojsoncallback=1&method=flickr.photos."+ FlickrBase.GET_RECENT_METHOD)
    @GET(FlickrBase.GET_RECENT_METHOD)
    Observable<PictureDeserializer.ResultValue> searchRecentPhotos(
            @QueryMap(encoded = true)Map<String, String> options
    );
}
