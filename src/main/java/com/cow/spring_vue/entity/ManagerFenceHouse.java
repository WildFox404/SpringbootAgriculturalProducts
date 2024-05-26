package com.cow.spring_vue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class ManagerFenceHouse {

    private String fhId;

    private String fhName;

    private String fhDesc;

    @JsonIgnore
    private String userId;

    private Timestamp fhCreateTime;

    private Timestamp fhUpdateTime;

    public String getFhId() {
        return fhId;
    }

    public void setFhId(String fhId) {
        this.fhId = fhId;
    }

    public String getFhName() {
        return fhName;
    }

    public void setFhName(String fhName) {
        this.fhName = fhName;
    }

    public String getFhDesc() {
        return fhDesc;
    }

    public void setFhDesc(String fhDesc) {
        this.fhDesc = fhDesc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getFhCreateTime() {
        return fhCreateTime;
    }

    public void setFhCreateTime(Timestamp fhCreateTime) {
        this.fhCreateTime = fhCreateTime;
    }

    public Timestamp getFhUpdateTime() {
        return fhUpdateTime;
    }

    public void setFhUpdateTime(Timestamp fhUpdateTime) {
        this.fhUpdateTime = fhUpdateTime;
    }
}
