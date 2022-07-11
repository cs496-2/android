package com.example.cs496_2.Retrofit;

import com.example.cs496_2.data.DTO.GetBodyToken;
import com.example.cs496_2.data.DTO.Travel;
import com.example.cs496_2.data.DTO.UpdateTravelBody;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/user/{userId}/travels")
    Call<JsonObject> getAllTravels(@Path("userId") String userId);

    @GET("/user/{userId}/{travelId}")
    Call<JsonObject> getTravelProject(@Path("userId") String userId, @Path("travelId") String travelId);

    @PUT("/user/{userId}/{travelId}")
    Call<JsonObject> updateTravel(@Path("userId") String userId, @Path("travelId") int travelId,
                                  @Field("travelName") String travelName,
                                  @Field("travelCountry") String travelCountry,
                                  @Field("startDate") Date startDate,
                                  @Field("endDate") Date endDate,
                                  @Field("foreignCurrency") String foreignCurrency,
                                  @Field("coverImg") String coverImg,
                                  @Field("token") String token);

    @GET("/user/{userId}/{travelId}/stats")
    Call<JsonObject> getTravelStat(@Path("userId") String userId, @Path("travelId") String travelId);

}
