package com.gemapps.rxpicapp.networking;

import com.gemapps.rxpicapp.networking.injection.NetProxy;

/**
 * Created by edu on 4/13/17.
 */

public class NetInjector {

    public static NetProxy getHomePictureRequester() {
        return new LocalHomePicturesNet();
    }
}
