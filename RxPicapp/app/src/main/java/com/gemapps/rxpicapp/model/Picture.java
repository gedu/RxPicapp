package com.gemapps.rxpicapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edu on 4/13/17.
 */

public class Picture implements Parcelable {

    @SerializedName("id")
    private String mId;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("secret")
    private String mSecret;
    @SerializedName("server")
    private String mServer;
    @SerializedName("farm")
    private String mFarm;
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

    private transient Author mAuthor;

    public Picture(Parcel in){
        mId = in.readString();
        mOwner = in.readString();
        mSecret = in.readString();
        mServer = in.readString();
        mFarm = in.readString();
        mTitle = in.readString();
        mDateTaken = in.readString();
        mOwnerName = in.readString();
        mCountFaves = in.readString();
        mCountComments = in.readString();
        mUrl = in.readString();
        mAuthor = in.readParcelable(Author.class.getClassLoader());
    }

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

    public String getFarm() {
        return mFarm;
    }

    public void setFarm(String farm) {
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

    public int getCountFaves() {
        return Integer.parseInt(mCountFaves);
    }

    public void setCountFaves(String countFaves) {
        mCountFaves = countFaves;
    }

    public int getCountComments() {
        return Integer.parseInt(mCountComments);
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

    public void setAuthor(Author author) {
        mAuthor = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mOwner);
        dest.writeString(mSecret);
        dest.writeString(mServer);
        dest.writeString(mFarm);
        dest.writeString(mTitle);
        dest.writeString(mDateTaken);
        dest.writeString(mOwnerName);
        dest.writeString(mCountFaves);
        dest.writeString(mCountComments);
        dest.writeString(mUrl);
        dest.writeParcelable(mAuthor, flags);
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public Author getAuthor() {
        return mAuthor;
    }
}
