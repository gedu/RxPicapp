package com.gemapps.rxpicapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gemapps.rxpicapp.networking.rest.FlickrBase;
import com.google.gson.annotations.SerializedName;

/**
 * Created by edu on 5/15/17.
 */

public class Author implements Parcelable {

    @SerializedName("id") private String mId;
    @SerializedName("nsid") private String mNsid;
    @SerializedName("iconserver") private String mIconServerId;
    @SerializedName("iconfarm") private String mIconFarmId;

    public Author(String id) {
        mId = id;
    }

    public Author(Parcel in){
        mId = in.readString();
        mNsid = in.readString();
        mIconServerId = in.readString();
        mIconFarmId = in.readString();
    }

    public String getIconUrl() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mNsid);
        dest.writeString(mIconServerId);
        dest.writeString(mIconFarmId);
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
