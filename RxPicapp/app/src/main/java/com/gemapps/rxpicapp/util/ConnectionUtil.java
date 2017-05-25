package com.gemapps.rxpicapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by edu on 5/25/17.
 */

public class ConnectionUtil {

    private static ConnectionUtil sInstance;
    private ConnectivityManager mManager;

    public ConnectionUtil(Context context) {
        mManager = (ConnectivityManager)context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static ConnectionUtil getInstance(Context context) {
        if(sInstance == null) sInstance = new ConnectionUtil(context);
        return sInstance;
    }

    public boolean hasConnection() {

        NetworkInfo activeNetwork = mManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public void clean() {
        sInstance = null;
    }
}
