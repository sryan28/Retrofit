package com.example.user.retrofit.interfaces;


import com.example.user.retrofit.models.ExampleModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ExampleApi {

    @POST("/posts")
    Call<ExampleModel> saveExample(@Body ExampleModel example);

}
