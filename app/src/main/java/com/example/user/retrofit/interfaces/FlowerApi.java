package com.example.user.retrofit.interfaces;

import com.example.user.retrofit.models.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/*
Create one abstract method for each feed that will be used
 */
public interface FlowerApi {

    /*
    Only include relative url for feed
    This is for a GET request
    This is where the feed exists

    Callback indicating the data retrieved
     */
    @GET("/feeds/flowers.json")
    Call<List<Flower>> getFeed();

}
