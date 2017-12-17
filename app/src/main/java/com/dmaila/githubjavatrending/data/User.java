package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

public class User {

    @Json(name = "login")
    private String login;
    @Json(name = "id")
    private Integer id;
    @Json(name = "avatar_url")
    private String avatarUrl;
    @Json(name = "type")
    private String type;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
