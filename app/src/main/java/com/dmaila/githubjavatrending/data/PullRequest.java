package com.dmaila.githubjavatrending.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class PullRequest implements Parcelable {

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
    private String closedAt;
    @Json(name = "merged_at")
    private String mergedAt;
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
        if (head.getRepo() != null) {
            return head.getRepo().getFullName();
        } else {
            return null;
        }
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

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public Object getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(String mergedAt) {
        this.mergedAt = mergedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeValue(this.id);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.issueUrl);
        dest.writeValue(this.number);
        dest.writeString(this.state);
        dest.writeString(this.title);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.body);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeString(this.closedAt);
        dest.writeString(this.mergedAt);
        dest.writeParcelable(this.head, flags);
        dest.writeString(this.repositoryFullName);
        dest.writeString(this.pullRequestOwnerLogin);
        dest.writeString(this.pullRequestOwnerAvatar);
    }

    public PullRequest() {
    }

    protected PullRequest(Parcel in) {
        this.url = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.htmlUrl = in.readString();
        this.issueUrl = in.readString();
        this.number = (Integer) in.readValue(Integer.class.getClassLoader());
        this.state = in.readString();
        this.title = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.body = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.closedAt = in.readString();
        this.mergedAt = in.readString();
        this.head = in.readParcelable(Head.class.getClassLoader());
        this.repositoryFullName = in.readString();
        this.pullRequestOwnerLogin = in.readString();
        this.pullRequestOwnerAvatar = in.readString();
    }

    public static final Parcelable.Creator<PullRequest> CREATOR = new Parcelable.Creator<PullRequest>() {
        @Override
        public PullRequest createFromParcel(Parcel source) {
            return new PullRequest(source);
        }

        @Override
        public PullRequest[] newArray(int size) {
            return new PullRequest[size];
        }
    };
}
