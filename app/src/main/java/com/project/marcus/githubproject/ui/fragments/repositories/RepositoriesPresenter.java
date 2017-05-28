package com.project.marcus.githubproject.ui.fragments.repositories;

import com.project.marcus.githubproject.model.Repository;

import java.util.List;

/**
 * Created by marcus on 28/05/17.
 */

public class RepositoriesPresenter implements RepositoriesMvpPresenter, RepositoriesMvpInteractor.OnUserList {
    private RepositoriesMvpView repositoriesMvpView;
    private RepositoriesMvpInteractor repositoriesMvpInteractor;

    public RepositoriesPresenter(RepositoriesMvpView repositoriesMvpView, RepositoriesMvpInteractor repositoriesMvpInteractor) {
        this.repositoriesMvpView = repositoriesMvpView;
        this.repositoriesMvpInteractor = repositoriesMvpInteractor;
    }

    @Override
    public void showRepositories() {
        if (getView() != null && repositoriesMvpInteractor != null) {
            getView().showProgress();
            repositoriesMvpInteractor.getRepositories(this);
        }
    }

    @Override
    public void searchRepositories(String query) {
        if (getView() != null && repositoriesMvpInteractor != null) {
            getView().showProgress();
            repositoriesMvpInteractor.getRepositoriesByName(query, this);
        }
    }

    @Override
    public void viewAndInteractorDestroy() {
        if (repositoriesMvpView != null) {
            repositoriesMvpView = null;
        }

        if (repositoriesMvpInteractor != null) {
            repositoriesMvpInteractor = null;
        }
    }

    @Override
    public void returnRepositories(List<Repository> repositoryList) {
        if (getView() != null) {
            getView().hideProgress();
            getView().loadRepositories(repositoryList);
        }
    }

    @Override
    public void returnError() {
        if (getView() != null) {
            getView().hideProgress();
            getView().showError();
        }

    }

    public RepositoriesMvpView getView() {
        return repositoriesMvpView;
    }
}