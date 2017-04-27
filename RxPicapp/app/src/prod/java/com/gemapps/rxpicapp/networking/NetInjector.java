package com.gemapps.rxpicapp.networking;

import com.gemapps.rxpicapp.data.homesource.HomePictureDataSource;
import com.gemapps.rxpicapp.data.homesource.HomePictureRemoteDataSource;

/**
 * Created by edu on 4/24/17.
 */

public class NetInjector {

    public static HomePictureDataSource getHomePictureRequester() {
        return new HomePictureRemoteDataSource();
    }
}
