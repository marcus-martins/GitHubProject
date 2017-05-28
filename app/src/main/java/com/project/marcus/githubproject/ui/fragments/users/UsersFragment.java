package com.project.marcus.githubproject.ui.fragments.users;

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
import com.project.marcus.githubproject.model.User;
import com.project.marcus.githubproject.network.GitHubClient;
import com.project.marcus.githubproject.ui.fragments.users.adapter.UserAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by marcus on 28/05/17.
 */

public class UsersFragment extends Fragment implements UsersMvpView {

    private UserAdapter userAdapter;
    private UsersMvpPresenter usersMvpPresenter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<User> userList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GitHubClient gitHubClient =
                ((GitHubProjectApplication) getActivity().getApplication()).getGitHubClient();

        UsersMvpInteractor usersMvpInteractor = new UsersInteractor(gitHubClient, compositeDisposable);

        usersMvpPresenter = new UsersPresenter(this, usersMvpInteractor);

        setupViews(view);

        showProgress();
        usersMvpPresenter.showUsers();

    }

    private void setupViews(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.user_progress);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        userAdapter = new UserAdapter(userList, getContext());
        recyclerView.setAdapter(userAdapter);

        swipeRefreshLayout =
                (SwipeRefreshLayout) view.findViewById(R.id.user_swipe_refresh);

        swipeRefreshLayout.setOnRefreshListener(() -> usersMvpPresenter.showUsers());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }

        if (usersMvpPresenter != null) {
            usersMvpPresenter.viewAndInteractorDestroy();
        }
    }


    @Override
    public void loadUsers(List<User> userList) {
        swipeRefreshLayout.setRefreshing(false);

        if (userList != null && userList.size() > 0) {
            hideProgress();
            this.userList.clear();
            this.userList.addAll(userList);
            userAdapter.notifyDataSetChanged();
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
        usersMvpPresenter.searchUsers(event.getQuery());
    }
}
