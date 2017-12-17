package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

public class Repo {

    @Json(name = "id")
    private Integer id;
    @Json(name = "name")
    private String name;
    @Json(name = "full_name")
    private String fullName;

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
}
