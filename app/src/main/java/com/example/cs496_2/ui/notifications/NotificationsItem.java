package com.example.cs496_2.ui.notifications;

import com.example.cs496_2.data.DTO.Travel;
import com.example.cs496_2.data.DTO.User;

public class NotificationsItem {
    public String category;
    public Float spend;

    public NotificationsItem(String category,
                             Float spend) {
        this.category = category;
        this.spend = spend;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getSpend() {
        return spend;
    }

    public void setSpend(Float spend) {
        this.spend = spend;
    }
}
