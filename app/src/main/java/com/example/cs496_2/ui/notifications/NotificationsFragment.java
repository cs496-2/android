package com.example.cs496_2.ui.notifications;

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
import com.example.cs496_2.databinding.FragmentNotificationsBinding;
import com.example.cs496_2.databinding.ItemAnalysisBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {
    private final String TAG = "-NotificationsFragment";

    private PieChart pieChart;

    private ArrayList<String> category_kr;
    private ArrayList<String> category_en_travel;
    private ArrayList<String> category_en_user;
    private RecyclerView spend_anlz_rv;
    private ArrayList<NotificationsItem> notificationsItemArrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        category_kr = new ArrayList<>(Arrays.asList("총 지출", "식비", "쇼핑", "관광", "교통", "숙박", "기타"));
        category_en_travel = new ArrayList<>(Arrays.asList("totalSpend", "mealSpend", "shopSpend", "tourSpend", "transportSpend", "hotelSpend", "etcSpend"));
        category_en_user = new ArrayList<>(Arrays.asList("personalTotalSpend", "personalMealSpend", "personalShopSpend", "personalTourSpend", "personalTransportSpend", "personalHotelSpend", "personalEtcSpend"));

        notificationsItemArrayList = new ArrayList<>();

        /*기존 여행 선택 시*/
        RetrofitAPI retrofitAPI = RetrofitSingleton.getRetrofitInstance().create(RetrofitAPI.class);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            Log.e(TAG, "I got travelId! " + intent.getStringExtra("travelId"));
            Call<JsonObject> travelJson = retrofitAPI.getTravelStat(MainActivity.user_id, intent.getStringExtra("travelId"));
            travelJson.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.e(TAG, "retrofit success");
                    JsonObject body = response.body();
                    JsonObject data = body.getAsJsonObject("data");
                    JsonObject travelForStat = data.getAsJsonObject("travelForStat");
                    Log.e(TAG, "travelForStat : " + travelForStat);
                    JsonObject travelUserPairForStat = data.getAsJsonObject("travelUserPairForStat");
                    Log.e(TAG, "travelUserPairForStat: " + travelUserPairForStat);
                    for (int i = 0; i < category_kr.size(); i++) {
                        notificationsItemArrayList.add(new NotificationsItem(
                                category_kr.get(i),
                                travelForStat.get(category_en_travel.get(i)).getAsFloat()
                                        + travelUserPairForStat.get(category_en_user.get(i)).getAsFloat()
                        ));
                    }
                    spend_anlz_rv.setAdapter(new NotificationsAdapter(getActivity(), notificationsItemArrayList));

                    pieChart = (PieChart) getView().findViewById(R.id.pie_chart);
                    initPieChart();
                    showPieChart();
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(TAG, "retrofit failed");
                }
            });
        }

        spend_anlz_rv = getView().findViewById(R.id.rv_spend_analysis);
        spend_anlz_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        spend_anlz_rv.setAdapter(new NotificationsAdapter(getActivity(), notificationsItemArrayList));
    }

    // function that modify the appearance of the chart
    // which should be called before showPieChart()
    private void initPieChart() {
        //using percentage as values instead of amount
        pieChart.setUsePercentValues(true);

        //remove the description label on the lower left corner, default true if not set
        pieChart.getDescription().setEnabled(false);

        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true);
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0);

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true);
        //adding animation so the entries pop up from 0 degree
        pieChart.animateY(1400, Easing.EaseInOutCubic);
        //setting the color of the hole in the middle, default white
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

    }

    // function responsible for capturing data input and output the pie chart with the data.
    private void showPieChart() {

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        //initializing data
        Map<String, Integer> typeAmountMap = new HashMap<>();
        Log.e(TAG, "category_kr : " + category_kr);
        Log.e(TAG, "notificationsItemArrayList : " + notificationsItemArrayList);
        for (int i = 1; i < category_kr.size(); i++) {
            if (Math.round(notificationsItemArrayList.get(i).getSpend()) != 0) {
                typeAmountMap.put(category_kr.get(i), Math.round(notificationsItemArrayList.get(i).getSpend()));
            }
        }

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        //input data and fit data into pie chart entry
        for (String type : typeAmountMap.keySet()) {
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);

        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);

        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        // undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();
        pieChart.setTouchEnabled(false);
        pieChart.setData(pieData);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}