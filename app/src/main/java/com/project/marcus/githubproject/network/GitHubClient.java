package com.project.marcus.githubproject.network;

import com.project.marcus.githubproject.model.Repository;
import com.project.marcus.githubproject.model.SearchRepository;
import com.project.marcus.githubproject.model.SearchUser;
import com.project.marcus.githubproject.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marcus on 28/05/17.
 */

public interface GitHubClient {

    @GET("users")
    Observable<List<User>> listUsers();

    @GET("search/users")
    Observable<SearchUser> searchUsers(
            @Query("q") String name
    );

    @GET("repositories")
    Observable<List<Repository>> listRepositories();

    @GET("search/repositories")
    Observable<SearchRepository> searchRepositories(
            @Query("q") String name
    );

}
