package com.example.temabianca;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WebService {

    private static Api api;
    public static final String API_BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Api getApi() {
        Gson gson = new GsonBuilder().setLenient().create();
        api = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api.class);
        return api;
    }

    public interface Api{
        @GET("/users")
        Call<List<UserModel>> getUsers();

        @GET("/todos")
        Call<List<ToDoModel>>getToDosForUser(@Query("userId")int user_id);
    }


}
