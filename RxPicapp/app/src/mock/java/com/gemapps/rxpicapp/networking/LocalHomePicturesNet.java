package com.gemapps.rxpicapp.networking;

import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;
import com.gemapps.rxpicapp.networking.injection.NetProxy;
import com.gemapps.rxpicapp.util.JsonUtil;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import static com.gemapps.rxpicapp.util.JsonUtil.loadJsonFromResources;

/**
 * Created by edu on 4/13/17.
 */

public class LocalHomePicturesNet implements NetProxy {

    private static final String JSON_EXAMPLE_NAME = "flickr_recent_json.json";

    @Override
    public Observable<PictureDeserializer.ResultValue> doGet() {

        return Observable.defer(new Callable<ObservableSource<? extends PictureDeserializer.ResultValue>>() {

            @Override
            public ObservableSource<? extends PictureDeserializer.ResultValue> call() throws Exception {
                String recentJson = loadJsonFromResources(getClass().getClassLoader(), JSON_EXAMPLE_NAME);
                return Observable.just(JsonUtil.PICTURE_DESERIALIZER.fromJson(recentJson,
                        PictureDeserializer.ResultValue.class));
            }
        });
    }
}
