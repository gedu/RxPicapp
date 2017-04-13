package com.gemapps.rxpicapp.networking.injection;

import com.gemapps.rxpicapp.networking.deserializer.PictureDeserializer;

import io.reactivex.Observable;

/**
 * Created by edu on 4/13/17.
 */

public interface NetProxy {

    Observable<PictureDeserializer.ResultValue> doGet();
}
