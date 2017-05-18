package com.gemapps.rxpicapp.data.homesource;

import android.util.Log;

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
        Log.d(TAG, "GET PICTURES: "+page);
        FlickrService searchService = mService.createService(FlickrService.class);
        return parsePictureWithAuthor(searchService
                .getRecentPhotos(buildSearchOptions("15", String.valueOf(page), null)));
//                .map(PictureDeserializer.ResultValue::getItems)
//                .flatMapIterable(pictures -> pictures)
//                .flatMap(new Function<Picture, ObservableSource<Author>>() {
//                    @Override
//                    public ObservableSource<Author> apply(Picture picture) throws Exception {
//                        Log.d(TAG, "2: "+picture.getTitle());
//                        PicappService userInfoService = new PicappService.Builder().setFactory(AuthorFactory.create()).build();
//                        FlickrService searchService = userInfoService.createService(FlickrService.class);
//                        return searchService.getAuthorInfo(RetrofitAdapter
//                                .buildUserInfoOptions(picture.getOwner()));
//                    }
//                }, (picture, author) -> {
//                    Log.d(TAG, "setting author: "+author.getId());
//                    picture.setAuthor(author);
//                    return picture;
//                })
//                .toList().toObservable()
//                .subscribeOn(Schedulers.io())
//                .share().replay();
    }
}