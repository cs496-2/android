package com.example.cs496_2.Retrofit;

import com.example.cs496_2.data.DTO.GetBodyToken;
import com.example.cs496_2.data.DTO.Travel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/user/{userId}/travels")
    Call<JsonObject> getAllTravels(@Path("userId") String userId);

    @GET("/user/{userId}/{travelId}")
    Call<JsonObject> getTravelProject(@Path("userId") String userId, @Path("travelId") String travelId);
}
