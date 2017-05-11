package com.gemapps.rxpicapp.data.homesource;

import android.util.Log;

import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.networking.rest.RetrofitAdapter;
import com.gemapps.rxpicapp.networking.rest.search.FlickrSearchService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.gemapps.rxpicapp.networking.rest.RetrofitAdapter.buildOptions;

/**
 * Created by edu on 4/17/17.
 */

public class HomePictureRemoteDataSource implements HomePictureDataSource {

    private static final String TAG = "HomePictureRemoteDataSo";

    @Override
    public Observable<List<Picture>> getPictures() {
        Log.d(TAG, "GET PICTURES");
        FlickrSearchService searchService = RetrofitAdapter.createService(FlickrSearchService.class);
        return searchService
                .searchRecentPhotos(buildOptions("15", "1", null))
                .subscribeOn(Schedulers.io())
                .map(PictureDeserializer.ResultValue::getPictures);
    }
}