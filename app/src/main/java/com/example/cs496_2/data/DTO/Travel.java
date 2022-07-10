package com.example.cs496_2.data.DTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Travel {
    public String travelId;
    public String travelName;
    public String travelCountry;
    public Date startDate;
    public Date endDate;
    public String foreignCurrency;
    public String coverImg;
    public String exchangeRate;
    public Double totalSpend;
    public Float mealSpend;
    public Float shopSpend;
    public Float tourSpend;
    public Float transportSpend;
    public Float hotelSpend;
    public Float etcSpend;
    public ArrayList<TravelSpend> travelSpends = null;
    public ArrayList<UserSpend> userSpends = null;
    public ArrayList<TravelUserPair> travelUserPairs = null;

    public Travel(String travelId,
                  String travelName,
                  String travelCountry,
                  Date startDate,
                  Date endDate,
                  String foreignCurrency,
                  String coverImg,
                  String exchangeRate,
                  Double totalSpend,
                  Float mealSpend,
                  Float shopSpend,
                  Float tourSpend,
                  Float transportSpend,
                  Float hotelSpend,
                  Float etcSpend,
                  ArrayList<TravelSpend> travelSpends,
                  ArrayList<UserSpend> userSpends,
                  ArrayList<TravelUserPair> travelUserPairs) {
        this.travelId = travelId;
        this.travelName = travelName;
        this.travelCountry = travelCountry;
        this.startDate = startDate;
        this.endDate = endDate;
        this.foreignCurrency = foreignCurrency;
        this.coverImg = coverImg;
        this.exchangeRate = exchangeRate;
        this.totalSpend = totalSpend;
        this.mealSpend = mealSpend;
        this.shopSpend = shopSpend;
        this.tourSpend = tourSpend;
        this.transportSpend = transportSpend;
        this.hotelSpend = hotelSpend;
        this.etcSpend = etcSpend;
        this.travelSpends = travelSpends;
        this.userSpends = userSpends;
        this.travelUserPairs = travelUserPairs;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Float getMealSpend() {
        return mealSpend;
    }

    public void setMealSpend(Float mealSpend) {
        this.mealSpend = mealSpend;
    }

    public Float getShopSpend() {
        return shopSpend;
    }

    public void setShopSpend(Float shopSpend) {
        this.shopSpend = shopSpend;
    }

    public Float getTourSpend() {
        return tourSpend;
    }

    public void setTourSpend(Float tourSpend) {
        this.tourSpend = tourSpend;
    }

    public Float getTransportSpend() {
        return transportSpend;
    }

    public void setTransportSpend(Float transportSpend) {
        this.transportSpend = transportSpend;
    }

    public Float getHotelSpend() {
        return hotelSpend;
    }

    public void setHotelSpend(Float hotelSpend) {
        this.hotelSpend = hotelSpend;
    }

    public Float getEtcSpend() {
        return etcSpend;
    }

    public void setEtcSpend(Float etcSpend) {
        this.etcSpend = etcSpend;
    }

    public ArrayList<TravelSpend> getTravelSpends() {
        return travelSpends;
    }

    public void setTravelSpends(ArrayList<TravelSpend> travelSpends) {
        this.travelSpends = travelSpends;
    }

    public ArrayList<UserSpend> getUserSpends() {
        return userSpends;
    }

    public void setUserSpends(ArrayList<UserSpend> userSpends) {
        this.userSpends = userSpends;
    }

    public ArrayList<TravelUserPair> getTravelUserPairs() {
        return travelUserPairs;
    }

    public void setTravelUserPairs(ArrayList<TravelUserPair> travelUserPairs) {
        this.travelUserPairs = travelUserPairs;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "travelId=" + travelId +
                ", travelName='" + travelName + '\'' +
                ", travelCountry='" + travelCountry + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", foreignCurrency='" + foreignCurrency + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", totalSpend=" + totalSpend +
                ", mealSpend=" + mealSpend +
                ", shopSpend=" + shopSpend +
                ", tourSpend=" + tourSpend +
                ", transportSpend=" + transportSpend +
                ", hotelSpend=" + hotelSpend +
                ", etcSpend=" + etcSpend +
                ", travelSpends=" + travelSpends +
                ", userSpends=" + userSpends +
                ", travelUserPairs=" + travelUserPairs +
                '}';
    }
}
