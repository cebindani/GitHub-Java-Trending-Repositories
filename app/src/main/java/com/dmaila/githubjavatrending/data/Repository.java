package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;


public class Repository {

    @Json(name = "id")
    private Integer id;
    @Json(name = "name")
    private String name;
    @Json(name = "full_name")
    private String fullName;
    @Json(name = "owner")
    private Owner owner;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
        return owner.getLogin();
    }

    public String getOwnerAvatarUrl() {
        return owner.getAvatarUrl();
    }

    public String getOwnerHtmlUrl() {
        return owner.getHtmlUrl();
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", owner=" + owner +
                ", description='" + description + '\'' +
                ", pullsUrl='" + pullsUrl + '\'' +
                ", stargazersCount=" + stargazersCount +
                ", forksCount=" + forksCount +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", ownerAvatarUrl='" + ownerAvatarUrl + '\'' +
                ", ownerHtmlUrl='" + ownerHtmlUrl + '\'' +
                '}';
    }
}
