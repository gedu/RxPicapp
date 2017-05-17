package com.gemapps.rxpicapp.networking.deserializer;

import com.gemapps.rxpicapp.util.JsonUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by edu on 5/17/17.
 */

public class FlickrCommentFactory extends Converter.Factory {

    public static FlickrCommentFactory create() {
        return new FlickrCommentFactory();
    }

    private FlickrCommentFactory(){}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return CommentConverter.INSTANCE;
    }

    private static class CommentConverter implements Converter<ResponseBody, CommentDeserializer.ResultValue> {

        static final CommentConverter INSTANCE = new CommentConverter();

        @Override
        public CommentDeserializer.ResultValue convert(ResponseBody value) throws IOException {
            return JsonUtil.COMMENT_DESERIALIZER.fromJson(value.string(),
                    CommentDeserializer.ResultValue.class);
        }
    }
}
