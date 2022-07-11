package com.example.cs496_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.data.DTO.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "----------MainActivity";

    public static String user_id = "abepje1";//todo: login 후 userId

    private RecyclerView travelRV;
    private TravelsAdapter travelsAdapter;
    private ArrayList<TravelsModel> travelsModels;
    private FloatingActionButton fabAddTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 여행 프로젝트 추가 버튼
        fabAddTravel = findViewById(R.id.fab);
        fabAddTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TravelActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        RetrofitAPI retrofitAPI = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
        Call<JsonObject> travelJson = retrofitAPI.getAllTravels("abepje1");
        travelJson.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(TAG, "retrofit successed");
                Log.d(TAG, String.valueOf(response));
                JsonObject res = response.body();
                JsonArray dataArr = res.getAsJsonArray("data");
                ArrayList<Travel> travels = new ArrayList<>();
                Log.e(TAG, String.valueOf(dataArr));
                // 리사이클러뷰에 표시할 데이터 리스트 생성.
                travelsModels = new ArrayList<>();
                for(int i = 0; i< dataArr.size();i++){
                    JsonObject travelItem = dataArr.get(i).getAsJsonObject();
                    Travel travel = new Gson().fromJson(travelItem, Travel.class);
                    travels.add(travel);
                    Log.e(TAG, i+" ::"+travels.get(i).toString());

                    travelsModels.add(new TravelsModel(
                            travel.getTravelId(),
                            travel.getTravelName(),
                            travel.getTravelCountry(),
                            travel.getStartDate(),
                            travel.getEndDate(),
                            travel.getCoverImg(),
                            travel.getTotalSpend()
                    ));
                }

                // 리사이클러뷰에 LinearLayoutManager 객체 지정.
                travelRV = findViewById(R.id.rv_travels);
                travelRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                // 리사이클러뷰에 travelAdapter 객체 지정.
                travelsAdapter = new TravelsAdapter(MainActivity.this, travelsModels);
                travelRV.setAdapter(travelsAdapter);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "retrofit failed");
            }
        });

    }
}