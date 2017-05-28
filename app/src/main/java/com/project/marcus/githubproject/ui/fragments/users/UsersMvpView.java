package com.project.marcus.githubproject.ui.fragments.users;

import com.project.marcus.githubproject.model.User;

import java.util.List;

/**
 * Created by marcus on 28/05/17.
 */

public interface UsersMvpView {

    void loadUsers(List<User> userList);

    void showProgress();

    void hideProgress();

    void showError();

}
