package com.project.marcus.githubproject.ui.fragments.users;

import com.project.marcus.githubproject.model.User;

import java.util.List;

/**
 * Created by marcus on 28/05/17.
 */

public class UsersPresenter implements UsersMvpPresenter, UsersMvpInteractor.OnUserList {
    private UsersMvpView usersMvpView;
    private UsersMvpInteractor usersMvpInteractor;

    public UsersPresenter(UsersMvpView usersMvpView, UsersMvpInteractor usersMvpInteractor) {
        this.usersMvpView = usersMvpView;
        this.usersMvpInteractor = usersMvpInteractor;
    }

    @Override
    public void showUsers() {
        if (getView() != null && usersMvpInteractor != null) {
            getView().showProgress();
            usersMvpInteractor.getUsers(this);
        }
    }

    @Override
    public void searchUsers(String query) {
        if (getView() != null && usersMvpInteractor != null) {
            getView().showProgress();
            usersMvpInteractor.getUsersByName(query, this);
        }
    }

    @Override
    public void viewAndInteractorDestroy() {
        if (usersMvpView != null) {
            usersMvpView = null;
        }

        if (usersMvpInteractor != null) {
            usersMvpInteractor = null;
        }
    }

    @Override
    public void returnUsers(List<User> userList) {
        if (getView() != null) {
            getView().hideProgress();
            getView().loadUsers(userList);
        }
    }

    @Override
    public void returnError() {
        if (getView() != null) {
            getView().hideProgress();
            getView().showError();
        }

    }

    public UsersMvpView getView() {
        return usersMvpView;
    }
}
