package com.example.cs496_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.data.DTO.Travel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.kakao.sdk.common.util.Utility;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = "LoginActivity";
    private ImageButton kakaoAuth, googleAuth;
    public static Context mContext;
    private SharedPreferences sharedPreferences;
    private User currentUser;
    private String userImageString = "";
    private Bitmap mBitmap;
    SharedPreferences.Editor editor;
    private Boolean isTrue = false;
    private Boolean nextIntent = false;
    private String meetingId;
    private Intent intent;
    private String loggedinUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getHashKey();
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                Log.wtf(TAG, "token check");
                if (oAuthToken != null) {
                    Log.wtf(TAG, "yes token");
                    Log.wtf(TAG, String.valueOf(oAuthToken));
                    Log.e(TAG, "oAuthToken.getAccessToken()=" + oAuthToken.getAccessToken());
                    Log.e(TAG, "oAuthToken.getRefreshToken()=" + oAuthToken.getRefreshToken());
                }
                if (throwable != null) {
                    // TBD
                    Log.wtf(TAG, "invoke: " + throwable.getLocalizedMessage());
                }
                Log.e(TAG, "before updateKakaoLoginUi");
                updateKakaoLoginUi();

                return null;
            }
        };

        kakaoAuth = findViewById(R.id.btn_login_google);  // 저는 카카오톡 로그인 버튼을 만들어서 했습니다.

        kakaoAuth.setOnClickListener(new View.OnClickListener() {   // 로그인 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick btn_login_google");
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    // 기기에 카카오톡이 깔려 있을 경우
                    Log.e(TAG, "yes kakaotalk app");
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                } else {
                    Log.e(TAG, "no kakaotalk app");
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });

        //oncreate 처음에 무조건 실행
        updateKakaoLoginUi();
    }

    public void updateKakaoLoginUi() {
        // 카카오 UI 가져오는 메소드 (로그인 핵심 기능)
        Log.wtf(TAG, "updateKakaoLoginUi");

        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                Log.wtf(TAG, "Entered UserApiClient Invoke...");
                if (user != null) {
                    Log.w(TAG, "USER COMES OUT " + user);
                    // 유저 정보가 정상 전달 되었을 경우
                    Log.i(TAG, "id " + user.getId());   // 유저의 고유 아이디를 불러옵니다.
//                    Log.i(TAG, "invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());  // 유저의 닉네임을 불러옵니다.
//                    Log.i(TAG, "userimage " + user.getKakaoAccount().getProfile().getProfileImageUrl());    // 유저의 이미지 URL을 불러옵니다.

                    // 이 부분에는 로그인이 정상적으로 되었을 경우 어떤 일을 수행할 지 적으면 됩니다.
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("userId", user.getKakaoAccount().getEmail());
                    jsonObject.addProperty("userName", user.getKakaoAccount().getProfile().getNickname());
                    jsonObject.addProperty("userPassword", "1234");
                    jsonObject.addProperty("age", 23);
                    jsonObject.addProperty("isActive", true);
                    loggedinUserId = user.getKakaoAccount().getEmail();


                    RetrofitAPI retrofitAPI = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
                    Call<JsonObject> travelJson = retrofitAPI.loginServer(jsonObject, loggedinUserId);
                    travelJson.enqueue(new Callback<JsonObject>() {


                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userId", loggedinUserId);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e(TAG, String.valueOf(t));
                            Log.e(TAG, "login failed");
//                            Log.e(TAG, )
                        }
                    });
                }
                if (throwable != null) {
                    // 로그인 시 오류 났을 때
                    // 키해시가 등록 안 되어 있으면 오류 납니다.
                    Log.wtf(TAG, "throwable 있음. no auth key!");
                    Log.e(TAG, "invoke throwable: " + throwable.getLocalizedMessage());
                }
                return null;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetrofitAPI retrofitAPI = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
        Call<JsonObject> travelJson = retrofitAPI.logoutServer(loggedinUserId);
        travelJson.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                loggedinUserId = null;
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, String.valueOf(t));
                Log.e(TAG, "retrofit failed");
            }
        });

    }

    private void getHashKey() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));    // 해시키를 로그로 찍어서 확인
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}
