package com.gemapps.rxpicapp.networking.deserializer;

import com.gemapps.rxpicapp.util.JsonUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by edu on 5/10/17.
 */

public class FlickrFactory extends Converter.Factory {

    public static FlickrFactory create() {
        return new FlickrFactory();
    }

    private FlickrFactory(){}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return FlickrConverter.INSTANCE;
    }

    private static class FlickrConverter implements Converter<ResponseBody, PictureDeserializer.ResultValue> {

        static final FlickrConverter INSTANCE = new FlickrConverter();

        @Override
        public PictureDeserializer.ResultValue convert(ResponseBody value) throws IOException {
            return JsonUtil.PICTURE_DESERIALIZER.fromJson(value.string(),
                    PictureDeserializer.ResultValue.class);
        }
    }
}
