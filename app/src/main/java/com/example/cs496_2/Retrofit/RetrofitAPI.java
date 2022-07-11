package com.example.cs496_2.Retrofit;

import com.example.cs496_2.data.DTO.PostNewTravel;
import com.example.cs496_2.data.DTO.UpdateTravel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitAPI {
    /* 여행 프로젝트에 유저 추가 */
    @POST("/user/{userId}/{travelId}/{invitedUserId}")
    Call<JsonObject> joinNewUserToTravel(@Path("userId") String userId, @Path("travelId") String travelId, @Path("invitedUserId") String invitedUserId,
                                         @Body JsonObject jsonToken);

    /* 여행 프로젝트에 새 지출 추가 */
    @POST("/user/{userId}/{travelId}")
    Call<JsonObject> postNewSpend(@Path("userId") String userId, @Path("travelId") int travelId,
                                  @Body JsonObject spend);

    /* 모든 여행 프로젝트 목록 */
    @GET("/user/{userId}/travels")
    Call<JsonObject> getAllTravels(@Path("userId") String userId);

    /* 특정 여행 프로젝트 정보 */
    @GET("/user/{userId}/{travelId}")
    Call<JsonObject> getTravelProject(@Path("userId") String userId, @Path("travelId") String travelId);

    /* 특정 여행 프로젝트의 지출 총합 정보 */
    @GET("/user/{userId}/{travelId}/stats")
    Call<JsonObject> getTravelStat(@Path("userId") String userId, @Path("travelId") String travelId);

    /* 특정 여행 프로젝트의 공동 지출+특정 개인 지출 정보 */
    @GET("/user/{userId}/{travelId}/spends")
    Call<JsonObject> getUserSpends(@Path("userId") String userId, @Path("travelId") String travelId);

    /* 새 여행 프로젝트 정보 저장 */
    @POST("/user/{userId}/travel")
    Call<JsonObject> postNewTravel(@Path("userId") String userId,
                                   @Body JsonObject newTravelJson);

    /* 특정 여행 프로젝트 정보 수정 */
    @PUT("/user/{userId}/{travelId}")
    Call<JsonObject> updateTravel(@Path("userId") String userId, @Path("travelId") int travelId,
                                  @Body JsonObject updateTravelJson);

    /* 특정 여행 프로젝트 삭제 */
    @DELETE("/user/{userId}/{travelId}")
    Call<JsonObject> deleteTravel(@Path("userId") String userId, @Path("travelId") int travelId);

    /* 특정 지출 내역 삭제 */
    @DELETE("/user/{userId}/{travelId}/delete/{spendId}/{isUserSpend}")
    Call<JsonObject> deleteSpend(@Path("userId") String userId, @Path("travelId") int travelId, @Path("spendId") int spendId, @Path("isUserSpend") Boolean isUserSpend);

    /* 특정 여행 프로젝트의 특정 유저 삭제 */
    @DELETE("/user/{userId}/{travelId}/{userId2}")
    Call<JsonObject> deleteUserFromTravel(@Path("userId") String userId, @Path("travelId") int travelId, @Path("userId2") String userId2);
}
