package com.example.cs496_2.data.DTO;

import java.util.Arrays;
import java.util.Date;

public class PostNewTravel {
    private String travelName;
    private String travelCountry;
    private String startDate;
    private String endDate;
    private String foreignCurrency;
    private String coverImg;
    private Float exchangeRate;
    private String token;

    public PostNewTravel(String travelName, String travelCountry, String startDate, String endDate, String foreignCurrency, String coverImg, Float exchangeRate, String token) {
        this.travelName = travelName;
        this.travelCountry = travelCountry;
        this.startDate = startDate;
        this.endDate = endDate;
        this.foreignCurrency = foreignCurrency;
        this.coverImg = coverImg;
        this.exchangeRate = exchangeRate;
        this.token = token;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public String getTravelCountry() {
        return travelCountry;
    }

    public void setTravelCountry(String travelCountry) {
        this.travelCountry = travelCountry;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(String foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "PostNewTravel{" +
                "travelName='" + travelName + '\'' +
                ", travelCountry='" + travelCountry + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", foreignCurrency='" + foreignCurrency + '\'' +
                ", coverImg=" +coverImg +
                ", exchangeRate=" + exchangeRate +
                ", token='" + token + '\'' +
                '}';
    }
}

