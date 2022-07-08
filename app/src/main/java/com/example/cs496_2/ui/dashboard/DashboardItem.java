package com.example.cs496_2.ui.dashboard;

public class DashboardItem {

    private String id;
    private String spend_name;
    private Float spend_amount;
    private String spend_date;
    private String consume_date;
    private String category;
    private String currency;

    // spend 공통 사용
    public DashboardItem(String id, String spend_name, Float spend_amount, String spend_date, String consume_date, String category, String currency) {
        this.id = id;
        this.spend_name = spend_name;
        this.spend_amount = spend_amount;
        this.spend_date = spend_date;
        this.consume_date = consume_date;
        this.category = category;
        this.currency = currency;
    }

    // getter & setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSpend_date() {
        return spend_date;
    }

    public void setSpend_date(String spend_date) {
        this.spend_date = spend_date;
    }

    public String getConsume_date() {
        return consume_date;
    }

    public void setConsume_date(String consume_date) {
        this.consume_date = consume_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
