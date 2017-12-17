package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

public class Head {

    @Json(name = "label")
    private String label;
    @Json(name = "ref")
    private String ref;
    @Json(name = "repo")
    private Repo repo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

}