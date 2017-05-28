package com.project.marcus.githubproject.ui.fragments.users.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.marcus.githubproject.R;

/**
 * Created by marcus on 25/05/17.
 */

public class CardHolderUser extends RecyclerView.ViewHolder {

    public TextView userName;
    public TextView userUrl;
    public ImageView userImage;

    public CardHolderUser(View itemView) {
        super(itemView);
        userName = (TextView) itemView.findViewById(R.id.user_name);
        userUrl = (TextView) itemView.findViewById(R.id.user_url);
        userImage = (ImageView) itemView.findViewById(R.id.user_image_view);
    }
}
