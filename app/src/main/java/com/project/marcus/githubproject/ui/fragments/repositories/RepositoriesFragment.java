package com.project.marcus.githubproject.ui.fragments.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.project.marcus.githubproject.GitHubProjectApplication;
import com.project.marcus.githubproject.R;
import com.project.marcus.githubproject.model.MessageEvent;
import com.project.marcus.githubproject.model.Repository;
import com.project.marcus.githubproject.network.GitHubClient;
import com.project.marcus.githubproject.ui.fragments.repositories.adapter.RepositoryAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by marcus on 28/05/17.
 */

public class RepositoriesFragment extends Fragment implements RepositoriesMvpView {

    private RepositoryAdapter repositoryAdapter;
    private RepositoriesMvpPresenter repositoriesMvpPresenter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Repository> repositoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repository, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GitHubClient gitHubClient =
                ((GitHubProjectApplication) getActivity().getApplication()).getGitHubClient();

        RepositoriesMvpInteractor repositoriesMvpInteractor = new RepositoriesInteractor(gitHubClient, compositeDisposable);

        repositoriesMvpPresenter = new RepositoriesPresenter(this, repositoriesMvpInteractor);

        setupViews(view);

        showProgress();
        repositoriesMvpPresenter.showRepositories();

    }

    private void setupViews(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.repository_progress);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.repository_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        repositoryAdapter = new RepositoryAdapter(repositoryList, getContext());
        recyclerView.setAdapter(repositoryAdapter);

        swipeRefreshLayout =
                (SwipeRefreshLayout) view.findViewById(R.id.repository_swipe_refresh);

        swipeRefreshLayout.setOnRefreshListener(() -> repositoriesMvpPresenter.showRepositories());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }

        if (repositoriesMvpPresenter != null) {
            repositoriesMvpPresenter.viewAndInteractorDestroy();
        }
    }


    @Override
    public void loadRepositories(List<Repository> repositoryList) {
        swipeRefreshLayout.setRefreshing(false);

        if (repositoryList != null && repositoryList.size() > 0) {
            hideProgress();
            this.repositoryList.clear();
            this.repositoryList.addAll(repositoryList);
            repositoryAdapter.notifyDataSetChanged();
        } else {
            showError();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        if (getView() != null) {
            Snackbar
                    .make(getView(), R.string.get_users_error, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        repositoriesMvpPresenter.searchRepositories(event.getQuery());
    }
}