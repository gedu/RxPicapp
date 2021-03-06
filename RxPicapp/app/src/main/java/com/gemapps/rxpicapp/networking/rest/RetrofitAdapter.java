package com.gemapps.rxpicapp.networking.rest;


import android.support.annotation.Nullable;

import com.gemapps.rxpicapp.BuildConfig;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by edu on 5/4/17.
 */

public class RetrofitAdapter implements FlickrBase {

    private static final String TAG = "RetrofitAdapter";

    private static long MIN_UPLOAD_DATE;
    {
        Calendar calendar = Calendar.getInstance();
        //TODO: handle this value from preference
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        MIN_UPLOAD_DATE = calendar.getTimeInMillis();
    }

    private static Map<String, String> baseOptions(){
        Map<String, String> options = new HashMap<>();
        options.put("format", "json");
        options.put("nojsoncallback", "1");
        options.put("min_upload_date", String.valueOf(MIN_UPLOAD_DATE));
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
        if(query != null) queries.put("text", query);

        return queries;
    }

    public static Map<String, String> buildGetProfileOptions(String userId) {
        Map<String, String> queries = baseOptions();
        queries.put("user_id", userId);
        return queries;
    }

    public static Map<String, String> buildCommentOptions(String photoId) {
        Map<String, String> queries = baseOptions();
        queries.put("photo_id", photoId);
        return queries;
    }

    public static Map<String, String> buildUserInfoOptions(String userId) {
        Map<String, String> queries = baseOptions();
        queries.put("user_id", userId);
        return queries;
    }
}
