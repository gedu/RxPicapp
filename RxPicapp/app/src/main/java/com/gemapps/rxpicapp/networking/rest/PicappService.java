package com.gemapps.rxpicapp.networking.rest;

import com.gemapps.rxpicapp.networking.NetInjector;
import com.gemapps.rxpicapp.networking.deserializer.FlickrSearchFactory;

import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by edu on 5/17/17.
 */

public class PicappService {

    private Converter.Factory mFactory;

    private PicappService(Builder builder){
        mFactory = builder.factory;
    }

    public <T> T createService(final Class<T> serviceClass) {

        Retrofit retrofit = NetInjector.getRetrofitBuilder()
                .addConverterFactory(mFactory)
                .build();

        return retrofit.create(serviceClass);
    }

    public static class Builder {

        private Converter.Factory factory;

        public Builder() {
            this.factory = FlickrSearchFactory.create();
        }

        public Builder setFactory(Converter.Factory factory) {
            this.factory = factory;
            return this;
        }

        public PicappService build() {
            return new PicappService(this);
        }
    }
}
