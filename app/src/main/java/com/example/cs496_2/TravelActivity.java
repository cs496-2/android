package com.example.cs496_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cs496_2.R;
import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.data.DTO.GetBodyToken;
import com.example.cs496_2.data.DTO.Travel;
import com.example.cs496_2.data.DTO.TravelSpend;
import com.example.cs496_2.data.DTO.TravelUserPair;
import com.example.cs496_2.data.DTO.User;
import com.example.cs496_2.data.DTO.UserSpend;
import com.example.cs496_2.data.TravelViewModel;
import com.example.cs496_2.ui.add.AddFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cs496_2.databinding.ActivityTravelBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelActivity extends AppCompatActivity {
    private String TAG = "------------TravelActivity";

    private ActivityTravelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // bottom navigation 구조 생성
        binding = ActivityTravelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_add)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_travel);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }
}