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
import com.example.cs496_2.data.DTO.Spend;
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
        String travelId = intent.getStringExtra("travelId");
        if (travelId != null) {
            Log.e(TAG, "I got travelId! " + intent.getStringExtra("travelId"));
            Call<JsonObject> travelJson = retrofitAPI.getUserSpends(MainActivity.user_id,travelId );
            travelJson.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.e(TAG, "retrofit success");
                    JsonObject body = response.body();
                    JsonArray data = body.getAsJsonArray("data");
                    Log.e(TAG, "data :: " + data);
                    for (int i = 0; i < data.size(); i++) {
                        JsonObject object = data.get(i).getAsJsonObject();
                        Log.e(TAG, "object : " + object);
                        Spend spend = new Gson().fromJson(object, Spend.class);
                        dashboardItems.add(new DashboardItem(
                                spend.getSpendId(),
                                spend.getSpendName(),
                                spend.getSpendAmount(),
                                spend.getCreatedDate(),
                                spend.isUseWon(),
                                spend.getSpendCategory(),
                                object.has("userSpendId")
                        ));
                    }
                    DashboardAdapter dashboardAdapter = new DashboardAdapter(getActivity(), dashboardItems);
                    spend_rv.setAdapter(dashboardAdapter);
                    Log.e("items", "아이템 개수 "+dashboardAdapter.getItemCount());
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(TAG, "retrofit failed");
                }
            });

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