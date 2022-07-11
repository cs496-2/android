package com.example.cs496_2.data;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cs496_2.data.DTO.Travel;
import com.example.cs496_2.data.DTO.TravelSpend;
import com.example.cs496_2.data.DTO.TravelUserPair;
import com.example.cs496_2.data.DTO.UserSpend;

import java.util.ArrayList;

public class TravelViewModel extends ViewModel {
    /*위의  frag에서 사용할 데이터*/
    private Boolean isExist=false;
    private Travel travel;
    private ArrayList<TravelUserPair> travelUserPairs;
    private ArrayList<TravelSpend> travelSpends;
    private ArrayList<UserSpend> userSpends;
    private Intent intent;

}
