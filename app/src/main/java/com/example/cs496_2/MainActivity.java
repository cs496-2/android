package com.example.cs496_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView travelRV;
    private TravelsAdapter travelsAdapter;
    private ArrayList<TravelsModel> travelsModels;
    private FloatingActionButton fabAddTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        travelsModels = new ArrayList<>();
        travelsModels.add(new TravelsModel(
                "대만",
                "대만",
                "220707",
                "220710",
                "원화",
                "신 타이완 달러"
        ));
         travelsModels.add(new TravelsModel(
                "독일",
                "독일",
                "220707",
                "220710",
                "원화",
                "유로"
        ));
         travelsModels.add(new TravelsModel(
                "영국",
                "영국",
                "220707",
                "220710",
                "원화",
                "유로"
        ));
         travelsModels.add(new TravelsModel(
                "프랑스",
                "프랑스",
                "220707",
                "220710",
                "원화",
                "유로"
        ));
         travelsModels.add(new TravelsModel(
                "그리스",
                "그리스",
                "220707",
                "220710",
                "원화",
                "유로"
        ));
         travelsModels.add(new TravelsModel(
                "이탈리아",
                "그리스",
                "220707",
                "220710",
                "원화",
                "유로"
        ));
         travelsModels.add(new TravelsModel(
                "튀르키예",
                "그리스",
                "220707",
                "220710",
                "원화",
                "유로"
        ));


        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        travelRV = findViewById(R.id.rv_travels);
        travelRV.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 travelAdapter 객체 지정.
        travelsAdapter = new TravelsAdapter(this, travelsModels);
        travelRV.setAdapter(travelsAdapter);

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

}