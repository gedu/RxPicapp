package com.gemapps.rxpicapp.networking.rest;

import android.support.annotation.Nullable;

import com.gemapps.rxpicapp.BuildConfig;
import com.gemapps.rxpicapp.networking.deserializer.FlickrFactory;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by edu on 5/4/17.
 */

public class RetrofitAdapter implements FlickrBase {

    private static final String TAG = "RetrofitAdapter";

    public static <T> T createService(final Class<T> serviceClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(FlickrFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl(FLICKR_URL)
                .build();

        return retrofit.create(serviceClass);
    }

    private static Map<String, String> baseOptions(){
        Map<String, String> options = new HashMap<>();
        options.put("format", "json");
        options.put("nojsoncallback", "1");
        options.put("api_key", BuildConfig.FLICKR_API_KEY);
        return options;
    }

    public static Map<String, String> buildSearchOptions(String perPage, String pagination,
                                                         @Nullable String query) {

        Map<String, String> queries = baseOptions();
        queries.put("sort", "relevance");
        queries.put("parse_tags", "1");
        queries.put("content_type", "7");
        queries.put("extras", "owner,date_taken,count_comments,count_faves,owner_name,url_n,url_c,url_b");
        queries.put("per_page", perPage);
        queries.put("page", pagination);
        if(query != null) queries.put("query", query);

        return queries;
    }

    public static Map<String, String> buildGetProfileOptions(String userId) {
        Map<String, String> queries = baseOptions();
        queries.put("user_id", userId);
        return queries;
    }
}
