package com.example.cs496_2;

import android.graphics.Bitmap;

import java.util.Date;

public class TravelsModel {
    private String travel_id;
    private String name;
    private String country;
    private Date start_date;
    private Date end_date;
    private String coverImg;
    private double total_spend = 0.0;

    // constructor 여행 프로젝트 생성 시 꼭 받아야 하는 정보
    public TravelsModel(String travel_id, String name, String country, Date start_date, Date end_date, String coverImg, Double total_spend) {
        this.travel_id = travel_id;
        this.name = name;
        this.country = country;
        this.start_date = start_date;
        this.end_date = end_date;
        this.coverImg = coverImg;
        this.total_spend = total_spend;
    }

    // getter & setter
    public String getTravel_id() {
        return travel_id;
    }
    public void setTravel_id(String travel_id) {
        this.travel_id = travel_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStart_date() {
        return start_date;
    }
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getCoverImg() {
        return coverImg;
    }
    public void setCoverImg(String  coverImg) {
        this.coverImg = coverImg;
    }

    public double getTotal_spend() {
        return total_spend;
    }
    public void setTotal_spend(int total_spend) {
        this.total_spend = total_spend;
    }
}
