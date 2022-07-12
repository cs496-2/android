package com.example.cs496_2.ui.schedule;

import java.util.Date;

public class ScheduleItem {
    private int scheduleId;


    private String scheduleName;
    private String date;
    private String time;
    private String location;

    public ScheduleItem(int scheduleId, String scheduleName, String date, String time, String location) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
