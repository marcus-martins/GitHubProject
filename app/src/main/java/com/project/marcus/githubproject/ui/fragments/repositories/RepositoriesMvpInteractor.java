package com.project.marcus.githubproject.ui.fragments.repositories;

import com.project.marcus.githubproject.model.Repository;

import java.util.List;

/**
 * Created by marcus on 28/05/17.
 */

public interface RepositoriesMvpInteractor {

    void getRepositories(OnUserList callback);

    void getRepositoriesByName(String query, OnUserList callback);

    interface OnUserList {

        void returnRepositories(List<Repository> repositoryList);

        void returnError();

    }

}
