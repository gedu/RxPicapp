package com.gemapps.rxpicapp.data.homesource;

import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.util.JsonUtil;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.gemapps.rxpicapp.util.JsonUtil.loadJsonFromResources;

/**
 * Created by edu on 4/24/17.
 */

public class FakeHomePicturesDataSource implements HomePictureDataSource {

    private static final String TAG = "FakeHomePicturesDataSou";
    private static final String JSON_EXAMPLE_NAME = "flickr_recent_json.json";

    @Override
    public Observable<List<Picture>> getPictures() {

        return Observable.fromCallable(readPictures())
                .subscribeOn(Schedulers.io())
                .map(getPicturesFromResult())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Callable<PictureDeserializer.ResultValue> readPictures() {
        return new Callable<PictureDeserializer.ResultValue>() {
            @Override
            public PictureDeserializer.ResultValue call() throws Exception {
                String pictureJson = loadPicturesFromFile();
                return parse(pictureJson);
            }
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
        return new Function<PictureDeserializer.ResultValue, List<Picture>>() {
            @Override
            public List<Picture> apply(PictureDeserializer.ResultValue result) throws Exception {
                return result.getPictures();
            }
        };
    }
}
