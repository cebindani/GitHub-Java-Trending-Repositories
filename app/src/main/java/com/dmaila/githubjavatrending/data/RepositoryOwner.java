package com.dmaila.githubjavatrending.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class RepositoryOwner implements Parcelable {

    @Json(name = "login")
    private String login;
    @Json(name = "avatar_url")
    private String avatarUrl;
    @Json(name = "html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.htmlUrl);
    }

    public RepositoryOwner() {
    }

    protected RepositoryOwner(Parcel in) {
        this.login = in.readString();
        this.avatarUrl = in.readString();
        this.htmlUrl = in.readString();
    }

    public static final Parcelable.Creator<RepositoryOwner> CREATOR = new Parcelable.Creator<RepositoryOwner>() {
        @Override
        public RepositoryOwner createFromParcel(Parcel source) {
            return new RepositoryOwner(source);
        }

        @Override
        public RepositoryOwner[] newArray(int size) {
            return new RepositoryOwner[size];
        }
    };
}
