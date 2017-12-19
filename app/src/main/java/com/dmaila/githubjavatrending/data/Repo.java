package com.dmaila.githubjavatrending.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Repo implements Parcelable {


    @Json(name = "name")
    private String pullRequestRepositoryName;
    @Json(name = "full_name")
    private String fullName;

    public String getPullRequestRepositoryName() {
        return pullRequestRepositoryName;
    }

    public void setPullRequestRepositoryName(String pullRequestRepositoryName) {
        this.pullRequestRepositoryName = pullRequestRepositoryName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pullRequestRepositoryName);
        dest.writeString(this.fullName);
    }

    public Repo() {
    }

    protected Repo(Parcel in) {
        this.pullRequestRepositoryName = in.readString();
        this.fullName = in.readString();
    }

    public static final Parcelable.Creator<Repo> CREATOR = new Parcelable.Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel source) {
            return new Repo(source);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}
