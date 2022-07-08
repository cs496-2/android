package com.example.cs496_2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496_2.R;
import com.example.cs496_2.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {


    // recycler view
    private RecyclerView spend_rv;
    private DashboardAdapter spend_adapter;
    private ArrayList<DashboardModel> dashboardModels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        dashboardModels = new ArrayList<>();
        dashboardModels.add(new DashboardModel("tv000001",
                "비행기값",
                800000F,
                "220307 09:30",
                "220709 09:30",
                "transportation",
                "won")
        );
        dashboardModels.add(new DashboardModel("ur000001",
                "기념품",
                300000F,
                "220108 09:30",
                "220709 09:30",
                "shopping",
                "won")
        );

        spend_rv = (RecyclerView) getView().findViewById(R.id.rv_spends);

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        spend_rv.setLayoutManager(layoutManager);

        // 리사이클러뷰에 travelAdapter 객체 지정.
        spend_adapter = new DashboardAdapter(getActivity(), dashboardModels);
        spend_rv.setAdapter(spend_adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}