package com.project.marcus.githubproject.ui.fragments.users;

/**
 * Created by marcus on 28/05/17.
 */

public interface UsersMvpPresenter {

    void showUsers();

    void searchUsers(String query);

    void viewAndInteractorDestroy();

}
