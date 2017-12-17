package com.dmaila.githubjavatrending.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;


public class Repository implements Parcelable {


    @Json(name = "id")
    private Integer id;
    @Json(name = "name")
    private String name;
    @Json(name = "full_name")
    private String fullName;
    @Json(name = "owner")
    private RepositoryOwner repositoryOwner;
    @Json(name = "description")
    private String description;
    @Json(name = "pulls_url")
    private String pullsUrl;
    @Json(name = "stargazers_count")
    private int stargazersCount;
    @Json(name = "forks_count")
    private int forksCount;
    private String ownerLogin;
    private String ownerAvatarUrl;
    private String ownerHtmlUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public RepositoryOwner getRepositoryOwner() {
        return repositoryOwner;
    }

    public void setRepositoryOwner(RepositoryOwner repositoryOwner) {
        this.repositoryOwner = repositoryOwner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getOwnerLogin() {
        return repositoryOwner.getLogin();
    }

    public String getOwnerAvatarUrl() {
        return repositoryOwner.getAvatarUrl();
    }

    public String getOwnerHtmlUrl() {
        return repositoryOwner.getHtmlUrl();
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", repositoryOwner=" + repositoryOwner +
                ", description='" + description + '\'' +
                ", pullsUrl='" + pullsUrl + '\'' +
                ", stargazersCount=" + stargazersCount +
                ", forksCount=" + forksCount +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", ownerAvatarUrl='" + ownerAvatarUrl + '\'' +
                ", ownerHtmlUrl='" + ownerHtmlUrl + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeParcelable(this.repositoryOwner, flags);
        dest.writeString(this.description);
        dest.writeString(this.pullsUrl);
        dest.writeInt(this.stargazersCount);
        dest.writeInt(this.forksCount);
        dest.writeString(this.ownerLogin);
        dest.writeString(this.ownerAvatarUrl);
        dest.writeString(this.ownerHtmlUrl);
    }

    public Repository() {
    }

    protected Repository(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.repositoryOwner = in.readParcelable(RepositoryOwner.class.getClassLoader());
        this.description = in.readString();
        this.pullsUrl = in.readString();
        this.stargazersCount = in.readInt();
        this.forksCount = in.readInt();
        this.ownerLogin = in.readString();
        this.ownerAvatarUrl = in.readString();
        this.ownerHtmlUrl = in.readString();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
