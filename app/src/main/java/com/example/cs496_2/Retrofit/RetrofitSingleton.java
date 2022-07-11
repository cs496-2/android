package com.example.cs496_2.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofit;
    private static RetrofitAPI retrofitAPI;

    private static final String BASE_URL = "http://192.249.21.206:3000";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetrofitAPI getRetrofitAPI() {
        return retrofitAPI;
    }
}
