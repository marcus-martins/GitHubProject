package com.project.marcus.githubproject.ui.fragments.users;

import com.project.marcus.githubproject.model.User;

import java.util.List;

/**
 * Created by marcus on 28/05/17.
 */

public interface UsersMvpInteractor {

    void getUsers(OnUserList callback);

    void getUsersByName(String query, OnUserList callback);

    interface OnUserList {

        void returnUsers(List<User> userList);

        void returnError();

    }

}
