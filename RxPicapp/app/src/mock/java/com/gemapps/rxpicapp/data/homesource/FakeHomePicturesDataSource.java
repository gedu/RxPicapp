package com.gemapps.rxpicapp.data.homesource;

import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.util.JsonUtil;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

import static com.gemapps.rxpicapp.util.JsonUtil.loadJsonFromResources;

/**
 * Created by edu on 4/24/17.
 */

public class FakeHomePicturesDataSource implements HomePictureDataSource {

    private static final String TAG = "FakeHomePicturesDataSou";
    private static final String JSON_EXAMPLE_NAME = "flickr_recent_json.json";

    @Override
    public ConnectableObservable<List<Picture>> getPictures(int page) {
        return Observable.fromCallable(readPictures())
                .subscribeOn(Schedulers.io())
                .map(getPicturesFromResult())
                .observeOn(AndroidSchedulers.mainThread())
                .share().replay();
    }

    private Callable<PictureDeserializer.ResultValue> readPictures() {
        return () -> {
            String pictureJson = loadPicturesFromFile();
            return parse(pictureJson);
        };
    }

    private String loadPicturesFromFile() {
        return loadJsonFromResources(getClass().getClassLoader(),
                JSON_EXAMPLE_NAME);
    }

    private PictureDeserializer.ResultValue parse(String pictureJson) {
        return JsonUtil.PICTURE_DESERIALIZER
                .fromJson(pictureJson, PictureDeserializer.ResultValue.class);
    }

    private Function<PictureDeserializer.ResultValue, List<Picture>> getPicturesFromResult() {
        return PictureDeserializer.ResultValue::getPictures;
    }
}
