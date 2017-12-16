package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

public class Owner {

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


}
