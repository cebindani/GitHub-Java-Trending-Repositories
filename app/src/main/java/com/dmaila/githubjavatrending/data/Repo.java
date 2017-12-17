package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

public class Repo {


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
}
