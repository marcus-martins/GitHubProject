package com.project.marcus.githubproject.model;

/**
 * Created by marcus on 28/05/17.
 */

public class MessageEvent {
    private String query;

    public MessageEvent(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}