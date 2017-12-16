package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

import java.util.List;


public class Repositories {
    @Json(name = "total_count")
    private Integer totalCount;
    @Json(name = "incomplete_results")
    private Boolean incompleteResults;
    @Json(name = "items")
    private List<Repository> repositories;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

}




