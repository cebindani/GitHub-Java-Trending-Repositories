package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

public class Head {

    @Json(name = "repo")
    private Repo repo;

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

}