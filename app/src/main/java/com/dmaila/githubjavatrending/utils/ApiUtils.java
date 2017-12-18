package com.dmaila.githubjavatrending.utils;

import okhttp3.HttpUrl;


public class ApiUtils {
    private static final String GITHUB_BASE_URL = "https://api.github.com";


    public static String buildPullRequestsURL(String owner, String repoName) {
        HttpUrl url = HttpUrl.parse(GITHUB_BASE_URL)
                .newBuilder()
                .addPathSegment("repos")
                .addPathSegment(owner)
                .addPathSegment(repoName)
                .addPathSegment("pulls")
                .addQueryParameter("state", "all")
                .build();
        return url.toString();

    }

    public static String buildSearchJavaRepositoriesURL(Object page) {

        HttpUrl url = HttpUrl.parse(GITHUB_BASE_URL)
                .newBuilder()
                .addPathSegment("search")
                .addPathSegment("repositories")
                .addQueryParameter("q", "language:Java")
                .addQueryParameter("sort", "stars")
                .addQueryParameter("page", String.valueOf(page))
                .build();

        return url.toString();

    }
}
