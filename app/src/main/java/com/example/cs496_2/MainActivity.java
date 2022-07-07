package com.example.cs496_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView travelRV;
    private TravelAdapter travelAdapter;
    private ArrayList<TravelModel> travelModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        travelModels = new ArrayList<>();
        travelModels.add(new TravelModel(
                "대만여행",
                "대만",
                "220707",
                "220710",
                "원화",
                "신 타이완 달러"
        ));
         travelModels.add(new TravelModel(
                "유럽여행",
                "독일",
                "220707",
                "220710",
                "원화",
                "유로"
        ));


        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        travelRV = findViewById(R.id.idRVtravel);
        travelRV.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        travelAdapter = new TravelAdapter(this, travelModels);
        travelRV.setAdapter(travelAdapter);

    }

}