package com.gemapps.rxpicapp.data.homesource;

import android.util.Log;

import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.networking.rest.FlickrService;
import com.gemapps.rxpicapp.networking.rest.PicappService;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

import static com.gemapps.rxpicapp.networking.rest.RetrofitAdapter.buildSearchOptions;

/**
 * Created by edu on 4/17/17.
 */

public class HomePictureRemoteDataSource implements HomePictureDataSource {

    private static final String TAG = "HomePictureRemoteDataSo";

    private PicappService mService;

    public HomePictureRemoteDataSource() {
        mService = new PicappService.Builder().build();
    }

    @Override
    public ConnectableObservable<List<Picture>> getPictures(int page) {
        Log.d(TAG, "GET PICTURES: "+page);
        FlickrService searchService = mService.createService(FlickrService.class);
        return searchService
                .searchRecentPhotos(buildSearchOptions("15", String.valueOf(page), null))
                .subscribeOn(Schedulers.io())
                .map(PictureDeserializer.ResultValue::getPictures)
                .share().replay();
    }
}