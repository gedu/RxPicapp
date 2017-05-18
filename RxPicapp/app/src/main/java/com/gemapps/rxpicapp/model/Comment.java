package com.gemapps.rxpicapp.model;

import com.gemapps.rxpicapp.networking.rest.FlickrBase;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by edu on 5/16/17.
 */

public class Comment {

    @SerializedName("id") private String mId;
    @SerializedName("author") private String mAuthorId;//used like nsid
    @SerializedName("realname") private String mAuthorName;
    @SerializedName("datecreate") private String mDateCreated;
    @SerializedName("iconserver") private String mIconServerId;
    @SerializedName("iconfarm") private String mIconFarmId;
    @SerializedName("_content") private String mMsg;

    public Comment() {
        mAuthorName = "Nobody";
    }

    public String getIconUrl() {
        return String.format(FlickrBase.USER_PIC_URL, mIconFarmId, mIconServerId, mAuthorId);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getAuthorId() {
        return mAuthorId;
    }

    public void setAuthorId(String authorId) {
        mAuthorId = authorId;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(String dateCreated) {
        mDateCreated = dateCreated;
    }

    public Date getPicDateFormatted(){
        Long ts = Long.parseLong(mDateCreated) * 1000L;
        return new Date(ts);
    }

    public String getIconServerId() {
        return mIconServerId;
    }

    public void setIconServerId(String iconServerId) {
        mIconServerId = iconServerId;
    }

    public String getIconFarmId() {
        return mIconFarmId;
    }

    public void setIconFarmId(String iconFarmId) {
        mIconFarmId = iconFarmId;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }
}
