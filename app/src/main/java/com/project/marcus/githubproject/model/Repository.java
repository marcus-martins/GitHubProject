package com.project.marcus.githubproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcus on 28/05/17.
 */

public class Repository {

    private String name;

    private String description;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
