package com.example.cs496_2.data.DTO;

import java.util.Date;

public class TravelSpend {

    public int travelSpendId;
    public String spendName;
    public Date createdDate;
    public int spendAmount;
    public boolean useWon;
    public int spendCategory;

    public TravelSpend(int travelSpendId, String spendName, Date createdDate, int spendAmount, boolean useWon, int spendCategory) {
        this.travelSpendId = travelSpendId;
        this.spendName = spendName;
        this.createdDate = createdDate;
        this.spendAmount = spendAmount;
        this.useWon = useWon;
        this.spendCategory = spendCategory;
    }

    public int getTravelSpendId() {
        return travelSpendId;
    }

    public void setTravelSpendId(int travelSpendId) {
        this.travelSpendId = travelSpendId;
    }

    public String getSpendName() {
        return spendName;
    }

    public void setSpendName(String spendName) {
        this.spendName = spendName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getSpendAmount() {
        return spendAmount;
    }

    public void setSpendAmount(int spendAmount) {
        this.spendAmount = spendAmount;
    }

    public boolean isUseWon() {
        return useWon;
    }

    public void setUseWon(boolean useWon) {
        this.useWon = useWon;
    }

    public int getSpendCategory() {
        return spendCategory;
    }

    public void setSpendCategory(int spendCategory) {
        this.spendCategory = spendCategory;
    }

    @Override
    public String toString() {
        return "TravelSpend{" +
                "travelSpendId=" + travelSpendId +
                ", spendName='" + spendName + '\'' +
                ", createdDate=" + createdDate +
                ", spendAmount=" + spendAmount +
                ", useWon=" + useWon +
                ", spendCategory=" + spendCategory +
                '}';
    }
}
