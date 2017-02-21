package com.example.user.retrofit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.retrofit.R;
import com.example.user.retrofit.adapters.FlowerAdapter;
import com.example.user.retrofit.adapters.GitHubProfileAdapter;
import com.example.user.retrofit.interfaces.ExampleApi;
import com.example.user.retrofit.interfaces.FlowerApi;
import com.example.user.retrofit.interfaces.GitHubApi;
import com.example.user.retrofit.models.ExampleModel;
import com.example.user.retrofit.models.Flower;
import com.example.user.retrofit.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Retrofit is a restful http lib that handles async task management
and can convert json directly to POJO

First define an interface that describes feeds being used
 */

public class MainActivity extends AppCompatActivity {

    List<Flower> flowerList;
    private RecyclerView rview;

    List<User.Items> items;

    //Root URLs
    public static String ENDPOINT =  "http://services.hanselandpetal.com";

    public static String ENDPOINT_GITHUB = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rview = (RecyclerView) findViewById(R.id.github_view);
        RecyclerView.LayoutManager rl = new LinearLayoutManager(this);
        rview.setLayoutManager(rl);

//        requestData();

        requestGitHubData();

//        requestSampleData();
    }

    /*
    Rest adapter to define where web service is
     */
    private void requestData() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlowerApi api = retrofit.create(FlowerApi.class);

        /*
        request: handles async request and parse from json to pojo list
        instantiate api; no async, no json parsing, less code, no http code
         */


       api.getFeed().enqueue(new Callback<List<Flower>>() {
           @Override
           public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
               if(response.isSuccessful()) {
                   flowerList = response.body();
                   updateDisplay();
               }
           }

           @Override
           public void onFailure(Call<List<Flower>> call, Throwable t) {

           }
       });
    }

    protected void updateDisplay() {
        FlowerAdapter adapter = new FlowerAdapter(this, R.layout.flower_item, flowerList);
        rview.setAdapter(adapter);
    }

    private void requestGitHubData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_GITHUB)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubApi api = retrofit.create(GitHubApi.class);

        api.getUser("sryan28").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    User user = response.body();
                    items = user.getItems();

                    updateDisplayGitHub();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void updateDisplayGitHub() {
        GitHubProfileAdapter adapter = new GitHubProfileAdapter(this, items);
        rview.setAdapter(adapter);
    }

//    private void requestSampleData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ENDPOINT3)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ExampleModel model = new ExampleModel();
//        model.setBody("Body");
//        model.setId(1);
//        model.setTitle("title");
//        model.setUserId(1);
//
//        ExampleApi api = retrofit.create(ExampleApi.class);
//        api.saveExample(model).enqueue(new Callback<ExampleModel>() {
//            @Override
//            public void onResponse(Call<ExampleModel> call, Response<ExampleModel> response) {
//                if(response.isSuccessful()) {
//                    System.out.println("SUCCESS" + response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ExampleModel> call, Throwable t) {
//                System.out.println("Fail");
//            }
//        });
//    }

}