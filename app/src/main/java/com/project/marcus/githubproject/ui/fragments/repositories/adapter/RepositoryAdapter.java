package com.project.marcus.githubproject.ui.fragments.repositories.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.project.marcus.githubproject.R;
import com.project.marcus.githubproject.model.Repository;

import java.util.List;

/**
 * Created by marcus on 25/05/17.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<CardHolderRepository> {
    private List<Repository> repositoryList;
    private Context context;

    public RepositoryAdapter(List<Repository> repositoryList, Context context) {
        this.repositoryList = repositoryList;
        this.context = context;
    }

    @Override
    public CardHolderRepository onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolderRepository(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolderRepository holder, int position) {
        Repository item = repositoryList.get(position);

        holder.repositoryName.setText(item.getName());

        if (item.getDescription() != null) {
            holder.repositoryDescription.setText(item.getDescription());
        }

        holder.repositoryUrl.setText(item.getHtmlUrl());
    }

    @Override
    public int getItemCount() {
        return repositoryList != null ? repositoryList.size() : 0;
    }
}