package com.example.cs496_2.data.DTO;

import android.media.Image;

import java.sql.Blob;
import java.util.ArrayList;

public class User{
    public String userId;
    public String userName;
    public String userPassword;
    public int age;
    public boolean isActive;
    public ArrayList<Object> photos;
    public ArrayList<UserSpend> userSpends;
    public ArrayList<TravelUserPair> travelUserPairs;

    public User(String userId,
                String userName,
                String userPassword,
                int age,
                boolean isActive,
                ArrayList<Object> photos,
                ArrayList<UserSpend> userSpends,
                ArrayList<TravelUserPair> travelUserPairs) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.age = age;
        this.isActive = isActive;
        this.photos = photos;
        this.userSpends = userSpends;
        this.travelUserPairs = travelUserPairs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<Object> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Object> photos) {
        this.photos = photos;
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
}