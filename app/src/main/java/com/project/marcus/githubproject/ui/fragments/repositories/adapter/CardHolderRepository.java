package com.project.marcus.githubproject.ui.fragments.repositories.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.project.marcus.githubproject.R;

/**
 * Created by marcus on 25/05/17.
 */

public class CardHolderRepository extends RecyclerView.ViewHolder {

    public TextView repositoryName;
    public TextView repositoryDescription;
    public TextView repositoryUrl;

    public CardHolderRepository(View itemView) {
        super(itemView);
        repositoryName = (TextView) itemView.findViewById(R.id.repository_name);
        repositoryDescription = (TextView) itemView.findViewById(R.id.repository_description);
        repositoryUrl = (TextView) itemView.findViewById(R.id.repository_url);
    }
}
