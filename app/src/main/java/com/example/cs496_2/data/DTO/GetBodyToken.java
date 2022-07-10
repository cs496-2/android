package com.example.cs496_2.data.DTO;

public class GetBodyToken {
    String token;

    public GetBodyToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
