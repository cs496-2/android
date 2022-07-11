package com.example.cs496_2.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_2.R;
import com.example.cs496_2.databinding.FragmentNotificationsBinding;
import com.example.cs496_2.databinding.ItemAnalysisBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private PieChart pieChart;

    private FragmentNotificationsBinding binding;
    private ItemAnalysisBinding itemBinding;
    private RecyclerView spend_anlz;

    private List<String> category;
    private NotificationsAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pieChart = (PieChart) getView().findViewById(R.id.pie_chart);
        initPieChart();
        showPieChart();

        category = new ArrayList<>();
        category.add("총 지출");
        category.add("식비");
        category.add("쇼핑");
        category.add("관광");
        category.add("교통");
        category.add("숙박");
        category.add("기타");

        //난 오 ㅐ 바인딩을 슬 수 없
//        binding = FragmentNotificationsBinding.inflate(getLayoutInflater());
//        binding.rvSpendAnalysis.setLayoutManager(new LinearLayoutManager(getActivity()));
//        binding.rvSpendAnalysis.setAdapter(new NotificationsAdapter(getActivity(), category));
        spend_anlz = getView().findViewById(R.id.rv_spend_analysis);
        spend_anlz.setLayoutManager(new LinearLayoutManager(getActivity()));
        spend_anlz.setAdapter(new NotificationsAdapter(getActivity(), category));
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
        typeAmountMap.put("식비", 200);
        typeAmountMap.put("쇼핑", 230);
        typeAmountMap.put("관광", 100);
        typeAmountMap.put("교통", 500);
        typeAmountMap.put("숙박", 50);
        typeAmountMap.put("기타", 120);

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