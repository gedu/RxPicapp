package com.gemapps.rxpicapp.networking;

import com.gemapps.rxpicapp.data.detailsource.DetailDataSource;
import com.gemapps.rxpicapp.data.detailsource.DetailRemoteDataSource;
import com.gemapps.rxpicapp.data.homesource.FakeHomePicturesDataSource;
import com.gemapps.rxpicapp.data.homesource.HomePictureDataSource;
import com.gemapps.rxpicapp.data.searchsource.SearchDataSource;
import com.gemapps.rxpicapp.data.searchsource.SearchRemoteDataSource;

/**
 * Created by edu on 4/13/17.
 */

public class NetInjector {

    public static HomePictureDataSource getHomePictureRequester() {
        return new FakeHomePicturesDataSource();//HomePictureRemoteDataSource();
    }

    public static SearchDataSource getSearchRequester() {
        //TODO: change this one for a fake
        return new SearchRemoteDataSource();
    }

    public static DetailDataSource getCommentRequester() {
        //TODO: change this one for a fake
        return new DetailRemoteDataSource();
    }
}
