package com.gemapps.rxpicapp.ui;

import android.util.LruCache;

import com.gemapps.rxpicapp.model.Picture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.observables.ConnectableObservable;

/**
 * Created by edu on 5/24/17.
 * Fast way to keep track of an observable for re-using when the configuration change
 */
public class PresenterSubscriptionStorage {

    private Map<Class<?>, ConnectableObservable<List<Picture>>> mSubscriptions;

    private static final PresenterSubscriptionStorage sInstance = new PresenterSubscriptionStorage();

    public static PresenterSubscriptionStorage getInstance() {
        return sInstance;
    }

    private PresenterSubscriptionStorage() {
        mSubscriptions = new HashMap<>(3);
    }

    public void saveSubscription(ConnectableObservable<List<Picture>> observable, Class<?> clazz) {

        mSubscriptions.put(clazz, observable);
    }

    public ConnectableObservable<List<Picture>> getSubscription(Class<?> clazz) {
        return mSubscriptions.get(clazz);
    }

    public void removeSubscription(Class<?> clazz) {
        mSubscriptions.remove(clazz);
    }
}
