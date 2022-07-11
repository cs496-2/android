package com.example.cs496_2.data.DTO;

import java.util.Date;

public class Spend {

    public int spendId;
    public String spendName;
    public Date createdDate;
    public Float spendAmount;
    public boolean useWon;
    public int spendCategory;

    public Spend(int spendId, String spendName, Date createdDate, Float spendAmount, boolean useWon, int spendCategory) {
        this.spendId = spendId;
        this.spendName = spendName;
        this.createdDate = createdDate;
        this.spendAmount = spendAmount;
        this.useWon = useWon;
        this.spendCategory = spendCategory;
    }

    public int getSpendId() {
        return spendId;
    }

    public void setSpendId(int spendId) {
        this.spendId = spendId;
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

    public Float getSpendAmount() {
        return spendAmount;
    }

    public void setSpendAmount(Float spendAmount) {
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
        return "Spend{" +
                "spendId=" + spendId +
                ", spendName='" + spendName + '\'' +
                ", createdDate=" + createdDate +
                ", spendAmount=" + spendAmount +
                ", useWon=" + useWon +
                ", spendCategory=" + spendCategory +
                '}';
    }
}
