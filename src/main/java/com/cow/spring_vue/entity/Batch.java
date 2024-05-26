package com.cow.spring_vue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class Batch {

    private String bBatchId;

    private Timestamp bTime;

    private String bStatus;

    private String bLocation;

    private String bQuarantine;

    @JsonIgnore
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getbQuarantine() {
        return bQuarantine;
    }

    public void setbQuarantine(String bQuarantine) {
        this.bQuarantine = bQuarantine;
    }

    public String getbBatchId() {
        return bBatchId;
    }

    public void setbBatchId(String bBatchId) {
        this.bBatchId = bBatchId;
    }

    public Timestamp getbTime() {
        return bTime;
    }

    public void setbTime(Timestamp bTime) {
        this.bTime = bTime;
    }

    public String getbStatus() {
        return bStatus;
    }

    public void setbStatus(String bStatus) {
        this.bStatus = bStatus;
    }

    public String getbLocation() {
        return bLocation;
    }

    public void setbLocation(String bLocation) {
        this.bLocation = bLocation;
    }
}
