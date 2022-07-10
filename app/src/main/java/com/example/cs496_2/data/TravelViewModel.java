package com.example.cs496_2.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cs496_2.data.DTO.Travel;

import java.util.ArrayList;

public class TravelViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Travel>> travels;

    public LiveData<ArrayList<Travel>> getTravels() {
        if (travels == null) {
            travels = new MutableLiveData<ArrayList<Travel>>();
            loadTravels();
        }
        return travels;
    }

    public void loadTravels() {

    }
}
