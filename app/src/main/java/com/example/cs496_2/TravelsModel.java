package com.example.cs496_2;

import android.graphics.Bitmap;

public class TravelsModel {
    private String travel_id;
    private String name;
    private String country;
    private String start_date;
    private String end_date;
    private String currency1;
    private String currency2;
    private String currency3;
    private Bitmap cover;
    private double total_spend = 0.0;
    private Float meal_spend;
    private Float shopping_spend;
    private Float tour_spend;
    private Float transportation_spend;
    private Float hotel_spend;
    private Float etc_spend;

    // constructor 여행 프로젝트 생성 시 꼭 받아야 하는 정보
    public TravelsModel(String name, String country, String start_date, String end_date, String currency1, String currency2) {
        this.name = name;
        this.country = country;
        this.start_date = start_date;
        this.end_date = end_date;
        this.currency1 = currency1; // 원화 추천
        this.currency2 = currency2;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getCurrency1() {
        return currency1;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public String getCurrency3() {
        return currency3;
    }

    public void setCurrency3(String currency3) {
        this.currency3 = currency3;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }

    public double getTotal_spend() {
        return total_spend;
    }

    public void setTotal_spend(int total_spend) {
        this.total_spend = total_spend;
    }

    public Float getMeal_spend() {
        return meal_spend;
    }

    public void setMeal_spend(Float meal_spend) {
        this.meal_spend = meal_spend;
    }

    public Float getShopping_spend() {
        return shopping_spend;
    }

    public void setShopping_spend(Float shopping_spend) {
        this.shopping_spend = shopping_spend;
    }

    public Float getTour_spend() {
        return tour_spend;
    }

    public void setTour_spend(Float tour_spend) {
        this.tour_spend = tour_spend;
    }

    public Float getTransportation_spend() {
        return transportation_spend;
    }

    public void setTransportation_spend(Float transportation_spend) {
        this.transportation_spend = transportation_spend;
    }

    public Float getHotel_spend() {
        return hotel_spend;
    }

    public void setHotel_spend(Float hotel_spend) {
        this.hotel_spend = hotel_spend;
    }

    public Float getEtc_spend() {
        return etc_spend;
    }

    public void setEtc_spend(Float etc_spend) {
        this.etc_spend = etc_spend;
    }
}
