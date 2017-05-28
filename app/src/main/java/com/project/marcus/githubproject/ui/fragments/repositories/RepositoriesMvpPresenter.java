package com.project.marcus.githubproject.ui.fragments.repositories;

/**
 * Created by marcus on 28/05/17.
 */

public interface RepositoriesMvpPresenter {

    void showRepositories();

    void searchRepositories(String query);

    void viewAndInteractorDestroy();

}
