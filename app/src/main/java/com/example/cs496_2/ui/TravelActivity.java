package com.example.cs496_2.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.cs496_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cs496_2.databinding.ActivityTravelBinding;

public class TravelActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        if (intent != null) {

        }
    }

}