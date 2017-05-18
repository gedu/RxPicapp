package com.gemapps.rxpicapp.networking;

import com.gemapps.rxpicapp.data.detailsource.DetailDataSource;
import com.gemapps.rxpicapp.data.detailsource.DetailRemoteDataSource;
import com.gemapps.rxpicapp.data.homesource.HomePictureDataSource;
import com.gemapps.rxpicapp.data.homesource.HomePictureRemoteDataSource;
import com.gemapps.rxpicapp.data.searchsource.SearchDataSource;
import com.gemapps.rxpicapp.data.searchsource.SearchRemoteDataSource;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.gemapps.rxpicapp.networking.rest.FlickrBase.FLICKR_URL;

/**
 * Created by edu on 4/24/17.
 */

public class NetInjector {

    public static HomePictureDataSource getHomePictureRequester() {
        return new HomePictureRemoteDataSource();
    }

    public static SearchDataSource getSearchRequester() {
        return new SearchRemoteDataSource();
    }

    public static DetailDataSource getCommentRequester() {
        return new DetailRemoteDataSource();
    }

    public static Retrofit.Builder getRetrofitBuilder() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl(FLICKR_URL);
    }
}
