package com.example.cs496_2.ui.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_2.MainActivity;
import com.example.cs496_2.R;
import com.example.cs496_2.Retrofit.RetrofitAPI;
import com.example.cs496_2.Retrofit.RetrofitSingleton;
import com.example.cs496_2.data.DTO.Travel;
import com.example.cs496_2.data.DTO.TravelSpend;
import com.example.cs496_2.data.DTO.UserSpend;
import com.example.cs496_2.ui.home.MemberAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    private final String TAG = "-----DashboardFragment";

    // recycler view
    private RecyclerView spend_rv;
    private DashboardAdapter spend_adapter;
    private ArrayList<DashboardItem> dashboardItems;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        dashboardItems = new ArrayList<>();
        spend_rv = (RecyclerView) getView().findViewById(R.id.rv_spends);
        spend_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        spend_rv.setAdapter(new DashboardAdapter(getActivity(), dashboardItems));
        /*기존 여행 선택 시*/
        RetrofitAPI retrofitAPI = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            Log.e(TAG, "I got travelId! " + intent.getStringExtra("travelId"));
            Call<JsonObject> travelJson = retrofitAPI.getTravelProject(MainActivity.user_id, intent.getStringExtra("travelId"));
            travelJson.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.e(TAG, "retrofit success");
                    JsonObject body = response.body();
                    JsonObject data = body.getAsJsonObject("data");
                    JsonObject resultTravelData = data.getAsJsonObject("resultTravelData");
                    Log.e(TAG, String.valueOf(resultTravelData));
                    JsonArray travelSpends = resultTravelData.getAsJsonArray("travelSpends");
                    for (int i = 0; i < travelSpends.size(); i++) {
                        JsonObject object = travelSpends.get(i).getAsJsonObject();
                        Log.e(TAG, "travelSpend" + i + String.valueOf(object));
                        TravelSpend travelSpend = new Gson().fromJson(object, TravelSpend.class);
                        dashboardItems.add(new DashboardItem(
                                travelSpend.getTravelSpendId(),
                                travelSpend.getSpendName(),
                                travelSpend.getSpendAmount(),
                                travelSpend.getCreatedDate(),
                                travelSpend.isUseWon(),
                                travelSpend.getSpendCategory()));
                    }
                    JsonArray userSpends = resultTravelData.getAsJsonArray("userSpends");
                    for (int i = 0; i < userSpends.size(); i++) {
                        JsonObject object = userSpends.get(i).getAsJsonObject();
                        Log.e(TAG, "userSpends" + i + String.valueOf(object));
                        UserSpend userSpend = new Gson().fromJson(object, UserSpend.class);
                        dashboardItems.add(new DashboardItem(
                                userSpend.getUserSpendId(),
                                userSpend.getSpendName(),
                                userSpend.getSpendAmount(),
                                userSpend.getCreatedDate(),
                                userSpend.isUseWon(),
                                userSpend.getSpendCategory()));
                    }
                    Collections.sort(dashboardItems, new ItemDateComparator());
                    spend_rv.setAdapter(new DashboardAdapter(getActivity(), dashboardItems));
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(TAG, "retrofit failed");
                }
            });
            Log.e("dashboard", String.valueOf(dashboardItems.size()));

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    class ItemDateComparator implements Comparator<DashboardItem> {
        @Override
        public int compare(DashboardItem i1, DashboardItem i2) {
            return i1.getCreated_date().toString().compareTo(i2.getCreated_date().toString());
        }
    }

}