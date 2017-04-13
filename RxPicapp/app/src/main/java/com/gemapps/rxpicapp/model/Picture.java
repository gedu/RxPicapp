package com.gemapps.rxpicapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edu on 4/13/17.
 */

public class Picture {

    @SerializedName("id")
    private String mId;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("secret")
    private String mSecret;
    @SerializedName("server")
    private String mServer;
    @SerializedName("farm")
    private int mFarm;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("ispublic")
    private int mIsPublic;
    @SerializedName("datetaken")
    private String mDateTaken;
    @SerializedName("ownername")
    private String mOwnerName;
    @SerializedName("count_faves")
    private String mCountFaves;
    @SerializedName("count_comments")
    private String mCountComments;
    @SerializedName("url_n")
    private String mUrl;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public String getSecret() {
        return mSecret;
    }

    public void setSecret(String secret) {
        mSecret = secret;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        mServer = server;
    }

    public int getFarm() {
        return mFarm;
    }

    public void setFarm(int farm) {
        mFarm = farm;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getIsPublic() {
        return mIsPublic;
    }

    public void setIsPublic(int isPublic) {
        mIsPublic = isPublic;
    }

    public String getDateTaken() {
        return mDateTaken;
    }

    public void setDateTaken(String dateTaken) {
        mDateTaken = dateTaken;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public void setOwnerName(String ownerName) {
        mOwnerName = ownerName;
    }

    public String getCountFaves() {
        return mCountFaves;
    }

    public void setCountFaves(String countFaves) {
        mCountFaves = countFaves;
    }

    public String getCountComments() {
        return mCountComments;
    }

    public void setCountComments(String countComments) {
        mCountComments = countComments;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
