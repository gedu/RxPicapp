package com.gemapps.rxpicapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by edu on 5/5/17.
 */

public class PicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
