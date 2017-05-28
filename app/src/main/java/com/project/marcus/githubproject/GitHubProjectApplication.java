package com.project.marcus.githubproject;

import android.app.Application;

import com.project.marcus.githubproject.network.GitHubClient;
import com.project.marcus.githubproject.network.RestServiceGenerator;

/**
 * Created by marcus on 28/05/17.
 */

public class GitHubProjectApplication extends Application {
    private static GitHubClient gitHubClient;

    @Override
    public void onCreate() {
        super.onCreate();
        gitHubClient = RestServiceGenerator.createService(GitHubClient.class);
    }

    public GitHubClient getGitHubClient() {
        return gitHubClient;
    }
}
