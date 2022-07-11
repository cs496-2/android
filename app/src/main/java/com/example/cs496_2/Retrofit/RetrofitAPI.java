package com.example.cs496_2.Retrofit;

import com.example.cs496_2.data.DTO.BodyToken;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/user/{userId}/travels")
    Call<JsonObject> getAllTravels(@Path("userId") String userId);

    @GET("/user/{userId}/{travelId}")
    Call<JsonObject> getTravelProject(@Path("userId") String userId, @Path("travelId") String travelId);

    @FormUrlEncoded
    @PUT("/user/{userId}/{travelId}")
    Call<JsonObject> updateTravel(@Path("userId") String userId, @Path("travelId") int travelId,
                                  @Field("travelName") String travelName,
                                  @Field("travelCountry") String travelCountry,
                                  @Field("startDate") String startDate,
                                  @Field("endDate") String endDate,
                                  @Field("foreignCurrency") String foreignCurrency,
                                  @Field("coverImg") String coverImg,
                                  @Field("token") String token);

    @FormUrlEncoded
    @DELETE("/user/{userId}/{travelId}")
    Call<JsonObject> deleteTravel(@Path("userId") String userId, @Path("travelId") int travelId,
                                  @FieldMap Map<String, String> keyValueMap);

    @GET("/user/{userId}/{travelId}/stats")
    Call<JsonObject> getTravelStat(@Path("userId") String userId, @Path("travelId") String travelId);

    @FormUrlEncoded
    @POST("/user/{userId}/{travelId}/{invitedUserId}")
    Call<JsonObject> joinNewUserToTravel(@Path("userId") String userId, @Path("travelId") String travelId, @Path("invitedUserId") String invitedUserId
            , @Body BodyToken bodyToken);

    @GET("/user/{userId}/{travelId}/spends")
    Call<JsonObject> getUserSpends(@Path("userId") String userId, @Path("travelId") String travelId);

    @FormUrlEncoded
    @POST("/user/{userId}/{travelId}")
    Call<JsonObject> postNewUserSpend(@Path("userId") String userId, @Path("travelId") int travelId,
                                      @FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("/user/{userId}/{travelId}")
    Call<JsonObject> postNewTravelSpend(@Path("userId") String userId, @Path("travelId") int travelId,
                                      @FieldMap Map<String, String> map);

}
