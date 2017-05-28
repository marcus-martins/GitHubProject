package com.project.marcus.githubproject.ui.fragments.users;

import com.project.marcus.githubproject.network.GitHubClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by marcus on 28/05/17.
 */

public class UsersInteractor implements UsersMvpInteractor {
    private GitHubClient gitHubClient;
    private CompositeDisposable compositeDisposable;

    public UsersInteractor(GitHubClient gitHubClient, CompositeDisposable compositeDisposable) {
        this.gitHubClient = gitHubClient;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void getUsers(OnUserList callback) {
        if (compositeDisposable != null && gitHubClient != null) {
            compositeDisposable.add(
                    gitHubClient
                            .listUsers()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userList -> {
                                        if (callback != null) {
                                            callback.returnUsers(userList);
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
    public void getUsersByName(String query, OnUserList callback) {
        if (compositeDisposable != null && gitHubClient != null) {
            compositeDisposable.add(
                    gitHubClient
                            .searchUsers(query)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userList -> {
                                        if (callback != null) {
                                            callback.returnUsers(userList.getItems());
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
