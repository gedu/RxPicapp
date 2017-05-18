package com.gemapps.rxpicapp.data.homesource;

import com.gemapps.rxpicapp.data.PictureMappingDataSource;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.rest.FlickrService;
import com.gemapps.rxpicapp.networking.rest.PicappService;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;

import static com.gemapps.rxpicapp.networking.rest.RetrofitAdapter.buildSearchOptions;

/**
 * Created by edu on 4/17/17.
 */

public class HomePictureRemoteDataSource extends PictureMappingDataSource
        implements HomePictureDataSource {

    private static final String TAG = "HomePictureRemoteDataSo";

    private PicappService mService;

    public HomePictureRemoteDataSource() {
        super();
        mService = new PicappService.Builder().build();
    }

    @Override
    public ConnectableObservable<List<Picture>> getPictures(int page) {
        FlickrService searchService = mService.createService(FlickrService.class);
        return parsePictureWithAuthor(searchService
                .getRecentPhotos(buildSearchOptions("15", String.valueOf(page), null)));
    }
}