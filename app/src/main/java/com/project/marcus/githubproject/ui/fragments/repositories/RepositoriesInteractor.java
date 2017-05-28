package com.project.marcus.githubproject.ui.fragments.repositories;

import com.project.marcus.githubproject.network.GitHubClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by marcus on 28/05/17.
 */

public class RepositoriesInteractor implements RepositoriesMvpInteractor {
    private GitHubClient gitHubClient;
    private CompositeDisposable compositeDisposable;

    public RepositoriesInteractor(GitHubClient gitHubClient, CompositeDisposable compositeDisposable) {
        this.gitHubClient = gitHubClient;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void getRepositories(OnUserList callback) {
        if (compositeDisposable != null && gitHubClient != null) {
            compositeDisposable.add(
                    gitHubClient
                            .listRepositories()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    repositoryList -> {
                                        if (callback != null) {
                                            callback.returnRepositories(repositoryList);
                                        }
                                    },
                                    throwable -> {
                                        if (callback != null) {
                                            callback.returnError();
                                        }
                                    })
            );
        }
    }

    @Override
    public void getRepositoriesByName(String query, OnUserList callback) {
        if (compositeDisposable != null && gitHubClient != null) {
            compositeDisposable.add(
                    gitHubClient
                            .searchRepositories(query)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    repositoryList -> {
                                        if (callback != null) {
                                            callback.returnRepositories(repositoryList.getItems());
                                        }
                                    },
                                    throwable -> {
                                        if (callback != null) {
                                            callback.returnError();
                                        }
                                    })
            );
        }
    }
}
