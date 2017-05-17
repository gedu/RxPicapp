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

public class FlickrSearchFactory extends Converter.Factory {

    public static FlickrSearchFactory create() {
        return new FlickrSearchFactory();
    }

    private FlickrSearchFactory(){}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return SearchConverter.INSTANCE;
    }

    private static class SearchConverter implements Converter<ResponseBody, PictureDeserializer.ResultValue> {

        static final SearchConverter INSTANCE = new SearchConverter();

        @Override
        public PictureDeserializer.ResultValue convert(ResponseBody value) throws IOException {
            return JsonUtil.PICTURE_DESERIALIZER.fromJson(value.string(),
                    PictureDeserializer.ResultValue.class);
        }
    }
}
