package com.project.marcus.githubproject.ui.fragments.repositories;

import com.project.marcus.githubproject.model.Repository;

import java.util.List;

/**
 * Created by marcus on 28/05/17.
 */

public interface RepositoriesMvpView {

    void loadRepositories(List<Repository> userList);

    void showProgress();

    void hideProgress();

    void showError();

}
