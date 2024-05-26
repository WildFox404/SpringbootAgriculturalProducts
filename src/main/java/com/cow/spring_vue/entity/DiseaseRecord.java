package com.cow.spring_vue.entity;

import java.sql.Timestamp;

public class DiseaseRecord {
    private String drAnimalId;

    private String drCure;

    private String drDesc;

    private String drId;

    private String drStatus;

    private Timestamp drCreateTime;

    private Timestamp drEndTime;

    public String getDrAnimalId() {
        return drAnimalId;
    }

    public void setDrAnimalId(String drAnimalId) {
        this.drAnimalId = drAnimalId;
    }

    public String getDrCure() {
        return drCure;
    }

    public void setDrCure(String drCure) {
        this.drCure = drCure;
    }

    public String getDrDesc() {
        return drDesc;
    }

    public void setDrDesc(String drDesc) {
        this.drDesc = drDesc;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getDrStatus() {
        return drStatus;
    }

    public void setDrStatus(String drStatus) {
        this.drStatus = drStatus;
    }

    public Timestamp getDrCreateTime() {
        return drCreateTime;
    }

    public void setDrCreateTime(Timestamp drCreateTime) {
        this.drCreateTime = drCreateTime;
    }

    public Timestamp getDrEndTime() {
        return drEndTime;
    }

    public void setDrEndTime(Timestamp drEndTime) {
        this.drEndTime = drEndTime;
    }
}
