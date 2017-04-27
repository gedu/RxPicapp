package com.gemapps.rxpicapp.networking;

import com.gemapps.rxpicapp.data.homesource.FakeHomePicturesDataSource;
import com.gemapps.rxpicapp.data.homesource.HomePictureDataSource;

/**
 * Created by edu on 4/13/17.
 */

public class NetInjector {

    public static HomePictureDataSource getHomePictureRequester() {
        return new FakeHomePicturesDataSource();
    }
}
