package com.dmaila.githubjavatrending;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class BackendTest {

    BackendUtils api;

    @Test
    public void buildSearchJavaRepositories_buildCorrectUrl() throws Exception {
        assertEquals("https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1", api.buildSearchJavaRepositoriesURL(1));
    }

    @Test
    public void buildPullRequestsURL_buildCorrectUrl() throws Exception {
        assertEquals("https://api.github.com/repos/elastic/elasticsearch/pulls?state=all", api.buildPullRequestsURL("elastic", "elasticsearch"));
    }

}