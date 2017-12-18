package com.dmaila.githubjavatrending;

import com.dmaila.githubjavatrending.utils.ApiUtils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class BackendTest {

    ApiUtils api;

    @Test
    public void buildSearchJavaRepositories_buildCorrectUrl() throws Exception {
        assertEquals("https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1", api.buildSearchJavaRepositoriesURL(1));
    }

    @Test
    public void buildPullRequestsURL_buildCorrectUrl() throws Exception {
        assertEquals("https://api.github.com/repos/elastic/elasticsearch/pulls?state=all", api.buildPullRequestsURL("elastic", "elasticsearch"));
    }

}