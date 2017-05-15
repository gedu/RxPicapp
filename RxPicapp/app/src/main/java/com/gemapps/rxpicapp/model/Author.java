package com.gemapps.rxpicapp.model;

import com.gemapps.rxpicapp.networking.rest.FlickrBase;
import com.google.gson.annotations.SerializedName;

/**
 * Created by edu on 5/15/17.
 */

public class Author {

    @SerializedName("id") private String mId;
    @SerializedName("nsid") private String mNsid;
    @SerializedName("iconserver") private String mIconServerId;
    @SerializedName("iconfarm") private String mIconFarmId;

    public String getIconUrl(){
        return String.format(FlickrBase.USER_PIC_URL, mIconFarmId, mIconServerId, mNsid);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getNsid() {
        return mNsid;
    }

    public void setNsid(String nsid) {
        mNsid = nsid;
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
}
