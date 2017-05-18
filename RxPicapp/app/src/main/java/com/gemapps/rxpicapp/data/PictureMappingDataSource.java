package com.gemapps.rxpicapp.data;

import android.util.Log;

import com.gemapps.rxpicapp.model.Author;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.deserializer.AuthorFactory;
import com.gemapps.rxpicapp.networking.deserializer.ResultHolder;
import com.gemapps.rxpicapp.networking.rest.FlickrService;
import com.gemapps.rxpicapp.networking.rest.PicappService;
import com.gemapps.rxpicapp.networking.rest.RetrofitAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by edu on 5/18/17.
 */

public abstract class PictureMappingDataSource {

    private static final String TAG = "PictureMappingDataSourc";

    private FlickrService mAuthorService;

    public PictureMappingDataSource() {
        PicappService pService = new PicappService.Builder()
                .setFactory(AuthorFactory.create())
                .build();
        mAuthorService = pService.createService(FlickrService.class);
    }

    protected ConnectableObservable<List<Picture>> parsePictureWithAuthor(Observable<ResultHolder> resultHolder) {

        return resultHolder
                .map(ResultHolder::getPictures)
                .flatMapIterable(pictures -> pictures)
                .flatMap(getEachAuthor(), setAuthorInPicture())
                .toList().toObservable()
                .subscribeOn(Schedulers.io())
                .share().replay();
    }

    private Function<Picture, ObservableSource<Author>> getEachAuthor() {
        return picture -> mAuthorService.getAuthorInfo(RetrofitAdapter
                .buildUserInfoOptions(picture.getOwner()));
    }

    private BiFunction<Picture, Author, Picture> setAuthorInPicture() {
        return (picture, author) -> {
            picture.setAuthor(author);
            return picture;
        };
    }

}
