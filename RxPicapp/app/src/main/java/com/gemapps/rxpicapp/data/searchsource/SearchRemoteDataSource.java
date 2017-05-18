package com.gemapps.rxpicapp.data.searchsource;

import com.gemapps.rxpicapp.data.PictureMappingDataSource;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.rest.PicappService;
import com.gemapps.rxpicapp.networking.rest.RetrofitAdapter;
import com.gemapps.rxpicapp.networking.rest.FlickrService;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;

/**
 * Created by edu on 5/15/17.
 */

public class SearchRemoteDataSource extends PictureMappingDataSource
        implements SearchDataSource {

    private PicappService mService;

    public SearchRemoteDataSource() {
        super();
        mService = new PicappService.Builder().build();
    }

    @Override
    public ConnectableObservable<List<Picture>> getPicturesFromQuery(int page, String query) {
        FlickrService searchService = mService.createService(FlickrService.class);
        return  parsePictureWithAuthor(searchService
                .searchPhotos(RetrofitAdapter.buildSearchOptions("15", String.valueOf(page), query)));
    }
}
