package com.gemapps.rxpicapp.networking.rest;

/**
 * Created by edu on 5/3/17.
 */

public interface FlickrBase {

    String FLICKR_URL = "https://api.flickr.com/services/rest/";
    /**
     * 1 iconFarm 2 iconServer 3 nsid
     */
    String USER_PIC_URL = "http://farm%s.staticflickr.com/%s/buddyicons/%s.jpg";

    String GET_RECENT_METHOD = "?method=flickr.photos.getRecent";
    String PEOPLE_PROFILE_METHOD = "?method=flickr.people.getInfo";
    String GET_COMMENTS_METHOD = "?method=flickr.photos.comments.getList";
}
