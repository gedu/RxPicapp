package com.gemapps.rxpicapp.data.searchsource;

import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.networking.rest.RetrofitAdapter;
import com.gemapps.rxpicapp.networking.rest.search.FlickrSearchService;

import java.util.List;

import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by edu on 5/15/17.
 */

public class SearchRemoteDataSource implements SearchDataSource {

    @Override
    public ConnectableObservable<List<Picture>> getPicturesFromQuery(int page, String query) {
        FlickrSearchService searchService = RetrofitAdapter.createService(FlickrSearchService.class);
        return searchService
                .searchRecentPhotos(RetrofitAdapter.buildSearchOptions("15", String.valueOf(page), query))
                .subscribeOn(Schedulers.io())
                .map(PictureDeserializer.ResultValue::getPictures)
                .share().replay();
    }
}
