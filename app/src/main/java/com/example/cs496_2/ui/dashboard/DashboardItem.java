package com.example.cs496_2.ui.dashboard;

import java.util.Date;

public class DashboardItem {

    // spend 공통 사용
    private int id;
    private String spend_name;
    private Float spend_amount;
    private Date created_date;
    private Boolean currency;
    private int category;
    private boolean isUserSpend;

    public DashboardItem(int id, String spend_name, Float spend_amount, Date created_date, Boolean currency, int category, boolean isUserSpend) {
        this.id = id;
        this.spend_name = spend_name;
        this.spend_amount = spend_amount;
        this.created_date = created_date;
        this.currency = currency;
        this.category = category;
        this.isUserSpend = isUserSpend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpend_name() {
        return spend_name;
    }

    public void setSpend_name(String spend_name) {
        this.spend_name = spend_name;
    }

    public Float getSpend_amount() {
        return spend_amount;
    }

    public void setSpend_amount(Float spend_amount) {
        this.spend_amount = spend_amount;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Boolean getCurrency() {
        return currency;
    }

    public void setCurrency(Boolean currency) {
        this.currency = currency;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isUserSpend() {
        return isUserSpend;
    }

    public void setUserSpend(boolean userSpend) {
        isUserSpend = userSpend;
    }
}
