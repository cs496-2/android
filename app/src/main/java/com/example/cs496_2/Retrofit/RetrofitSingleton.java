package com.example.cs496_2.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofit;
    private static RetrofitAPI retrofitAPI;

    private static final String BASE_URL = "http://192.249.21.206:3000";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
            GsonConverterFactory factory = GsonConverterFactory.create(gson);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(factory)
                    .build();
        }
        return retrofit;
    }


    public static RetrofitAPI getRetrofitAPI() {
        return retrofitAPI;
    }
}
