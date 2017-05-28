package com.project.marcus.githubproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcus on 28/05/17.
 */

public class User {

    private String login;

    @SerializedName("avatar_url")
    private String photo;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}