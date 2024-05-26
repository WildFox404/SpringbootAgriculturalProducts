package com.cow.spring_vue.model;

public class LoginResponse {
    private String username;
    private int statusCode;
    private int id;


    public LoginResponse(String username, int statusCode, int id) {
        this.username = username;
        this.statusCode = statusCode;
        this.id=id;
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
