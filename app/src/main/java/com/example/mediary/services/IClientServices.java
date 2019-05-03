package com.example.mediary.services;

import com.example.mediary.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IClientServices {

    @GET("/")
    public Call<String> test();

    @GET("/login")
    public Call<String> goLogin();

    @GET("/users/{username}")
    public Call<User> getUser(@Path("username") String username);

    @GET("/repos/{owner}/{repo}/contributors")
    public Call<List<String>> contributor(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}

