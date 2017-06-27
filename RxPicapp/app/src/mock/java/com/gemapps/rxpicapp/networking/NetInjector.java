package com.gemapps.rxpicapp.networking;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.gemapps.rxpicapp.data.detailsource.DetailDataSource;
import com.gemapps.rxpicapp.data.detailsource.DetailRemoteDataSource;
import com.gemapps.rxpicapp.data.homesource.FakeHomePicturesDataSource;
import com.gemapps.rxpicapp.data.homesource.HomePictureDataSource;
import com.gemapps.rxpicapp.data.homesource.HomePictureRemoteDataSource;
import com.gemapps.rxpicapp.data.searchsource.SearchDataSource;
import com.gemapps.rxpicapp.data.searchsource.SearchRemoteDataSource;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.gemapps.rxpicapp.networking.rest.FlickrBase.FLICKR_URL;

/**
 * Created by edu on 4/13/17.
 */

public class NetInjector {

    public static HomePictureDataSource getHomePictureRequester() {
        return new FakeHomePicturesDataSource();
    }

    public static SearchDataSource getSearchRequester() {
        //TODO: change this one for a fake
        return new SearchRemoteDataSource();
    }

    public static DetailDataSource getCommentRequester() {
        //TODO: change this one for a fake
        return new DetailRemoteDataSource();
    }

    public static Retrofit.Builder getRetrofitBuilder() {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl(FLICKR_URL);
    }
}
