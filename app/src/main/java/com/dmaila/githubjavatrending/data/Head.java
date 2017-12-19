package com.dmaila.githubjavatrending.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Head implements Parcelable {

    @Json(name = "repo")
    private Repo repo;

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.repo, flags);
    }

    public Head() {
    }

    protected Head(Parcel in) {
        this.repo = in.readParcelable(Repo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Head> CREATOR = new Parcelable.Creator<Head>() {
        @Override
        public Head createFromParcel(Parcel source) {
            return new Head(source);
        }

        @Override
        public Head[] newArray(int size) {
            return new Head[size];
        }
    };
}