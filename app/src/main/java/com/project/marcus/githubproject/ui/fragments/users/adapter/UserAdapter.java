package com.project.marcus.githubproject.ui.fragments.users.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.project.marcus.githubproject.R;
import com.project.marcus.githubproject.model.User;

import java.util.List;

/**
 * Created by marcus on 25/05/17.
 */

public class UserAdapter extends RecyclerView.Adapter<CardHolderUser> {
    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public CardHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolderUser(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_user, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolderUser holder, int position) {
        User item = userList.get(position);

        Glide.with(context)
                .load(item.getPhoto())
                .centerCrop()
                .into(holder.userImage);

        holder.userName.setText(item.getLogin());
        holder.userUrl.setText(item.getHtmlUrl());
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }
}