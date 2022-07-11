package com.example.cs496_2.data.DTO;

import java.util.Date;

public class UserSpend {

    public int userSpendId;
    public String spendName;
    public Date createdDate;
    public int spendAmount;
    public boolean useWon;
    public int spendCategory;

    public UserSpend(int userSpendId, String spendName, Date createdDate, int spendAmount, boolean useWon, int spendCategory) {
        this.userSpendId = userSpendId;
        this.spendName = spendName;
        this.createdDate = createdDate;
        this.spendAmount = spendAmount;
        this.useWon = useWon;
        this.spendCategory = spendCategory;
    }

    public int getUserSpendId() {
        return userSpendId;
    }

    public void setUserSpendId(int userSpendId) {
        this.userSpendId = userSpendId;
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
        return "UserSpend{" +
                "userSpendId=" + userSpendId +
                ", spendName='" + spendName + '\'' +
                ", createdDate=" + createdDate +
                ", spendAmount=" + spendAmount +
                ", useWon=" + useWon +
                ", spendCategory=" + spendCategory +
                '}';
    }
}

