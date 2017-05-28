package com.project.marcus.githubproject.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.project.marcus.githubproject.R;
import com.project.marcus.githubproject.model.MessageEvent;
import com.project.marcus.githubproject.ui.fragments.repositories.RepositoriesFragment;
import com.project.marcus.githubproject.ui.fragments.users.UsersFragment;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_users:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content, new UsersFragment())
                                .commit();
                        return true;
                    case R.id.navigation_repositories:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content, new RepositoriesFragment())
                                .commit();
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new UsersFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        EventBus.getDefault().post(new MessageEvent(query));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
