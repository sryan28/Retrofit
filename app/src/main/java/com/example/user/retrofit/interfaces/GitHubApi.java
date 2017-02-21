package com.example.user.retrofit.interfaces;

import com.example.user.retrofit.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("/search/users")
    Call<User> getUser(@Query("q") String username);

//    @GET("/search/{id}")
//    Call<User> getUserId(@Path("id") String id);


}
