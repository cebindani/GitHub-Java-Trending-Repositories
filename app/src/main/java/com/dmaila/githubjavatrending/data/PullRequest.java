package com.dmaila.githubjavatrending.data;

import com.squareup.moshi.Json;

import java.sql.Timestamp;

public class PullRequest {

    @Json(name = "url")
    private String url;
    @Json(name = "id")
    private Integer id;
    @Json(name = "html_url")
    private String htmlUrl;
    @Json(name = "issue_url")
    private String issueUrl;
    @Json(name = "number")
    private Integer number;
    @Json(name = "state")
    private String state;
    @Json(name = "title")
    private String title;
    @Json(name = "user")
    private User user;
    @Json(name = "body")
    private String body;
    @Json(name = "created_at")
    private String createdAt;
    @Json(name = "updated_at")
    private String updatedAt;
    @Json(name = "closed_at")
    private Timestamp closedAt;
    @Json(name = "merged_at")
    private Timestamp mergedAt;
    @Json(name = "head")
    private Head head;

    private String repositoryFullName;
    private String pullRequestOwnerLogin;
    private String pullRequestOwnerAvatar;

    public String getPullRequestOwnerLogin() {
        return user.getLogin();
    }

    public void setPullRequestOwnerLogin(String pullRequestOwnerLogin) {
        this.pullRequestOwnerLogin = pullRequestOwnerLogin;
    }

    public String getPullRequestOwnerAvatar() {
        return user.getAvatarUrl();
    }

    public void setPullRequestOwnerAvatar(String pullRequestOwnerAvatar) {
        this.pullRequestOwnerAvatar = pullRequestOwnerAvatar;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public String getRepositoryFullName() {
        return head.getRepo().getFullName();
    }

    public void setRepositoryFullName(String repositoryFullName) {
        this.repositoryFullName = repositoryFullName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }


    public String getIssueUrl() {
        return issueUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Timestamp closedAt) {
        this.closedAt = closedAt;
    }

    public Object getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(Timestamp mergedAt) {
        this.mergedAt = mergedAt;
    }

}
