package com.gemapps.rxpicapp.networking.deserializer;

import com.gemapps.rxpicapp.model.Author;
import com.gemapps.rxpicapp.util.JsonUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by edu on 5/18/17.
 */

public class AuthorFactory extends Converter.Factory {

    public static AuthorFactory create() {
        return new AuthorFactory();
    }

    private AuthorFactory(){}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return AuthorConverter.INSTANCE;
    }

    private static class AuthorConverter implements Converter<ResponseBody, Author> {

        static final AuthorConverter INSTANCE = new AuthorConverter();

        @Override
        public Author convert(ResponseBody value) throws IOException {
            return JsonUtil.USER_DESERIALIZER.fromJson(value.string(),
                    Author.class);
        }
    }
}
