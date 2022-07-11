package com.example.cs496_2.data.DTO;

public class CountryJson {
    private String name;
    private String money;
    private String ISO;
    private Float exchange;

    public CountryJson(String name, String money, String ISO, Float exchange) {
        this.name = name;
        this.money = money;
        this.ISO = ISO;
        this.exchange = exchange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getISO() {
        return ISO;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    public Float getExchange() {
        return exchange;
    }

    public void setExchange(Float exchange) {
        this.exchange = exchange;
    }
}
