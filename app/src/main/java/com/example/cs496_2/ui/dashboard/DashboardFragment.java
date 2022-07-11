package com.example.cs496_2.ui.dashboard;

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

import com.example.cs496_2.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {


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
        dashboardItems.add(new DashboardItem("1",
                "시작",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("2",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        dashboardItems.add(new DashboardItem("3",
                "비행기값",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("4",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        dashboardItems.add(new DashboardItem("5",
                "비행기값",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("6",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        dashboardItems.add(new DashboardItem("7",
                "비행기값",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("8",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        dashboardItems.add(new DashboardItem("9",
                "비행기값",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("10",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        dashboardItems.add(new DashboardItem("11",
                "비행기값",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("12",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        dashboardItems.add(new DashboardItem("13",
                "비행기값",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("14",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        dashboardItems.add(new DashboardItem("15",
                "비행기값마지막",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardItems.add(new DashboardItem("16",
                "마지막",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );
        Log.e("dashboard", String.valueOf(dashboardItems.size()));

        spend_rv = (RecyclerView) getView().findViewById(R.id.rv_spends);

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        spend_rv.setLayoutManager(layoutManager);

        // 리사이클러뷰에 travelAdapter 객체 지정.
        spend_adapter = new DashboardAdapter(getActivity(), dashboardItems);
        spend_rv.setAdapter(spend_adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}