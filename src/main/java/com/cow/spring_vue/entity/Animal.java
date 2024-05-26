package com.cow.spring_vue.entity;

import java.sql.Timestamp;

public class Animal {
    private String aAnimalId;

    //批次编号
    private String aBatchId;

    private String aGender;

    private String aHurdlesId;

    private String aHealthy;

    private String aInoculate;

    private Timestamp aTime;

    private Integer aWeightInit;

    private Integer aWeightNow;

    private String aStatus;

    public String getaStatus() {
        return aStatus;
    }

    public void setaStatus(String aStatus) {
        this.aStatus = aStatus;
    }

    public String getaHealthy() {
        return aHealthy;
    }

    public void setaHealthy(String aHealthy) {
        this.aHealthy = aHealthy;
    }

    public String getaAnimalId() {
        return aAnimalId;
    }

    public void setaAnimalId(String aAnimalId) {
        this.aAnimalId = aAnimalId;
    }

    public String getaBatchId() {
        return aBatchId;
    }

    public void setaBatchId(String aBatchId) {
        this.aBatchId = aBatchId;
    }

    public String getaGender() {
        return aGender;
    }

    public void setaGender(String aGender) {
        this.aGender = aGender;
    }

    public String getaHurdlesId() {
        return aHurdlesId;
    }

    public void setaHurdlesId(String aHurdlesId) {
        this.aHurdlesId = aHurdlesId;
    }

    public String getaInoculate() {
        return aInoculate;
    }

    public void setaInoculate(String aInoculate) {
        this.aInoculate = aInoculate;
    }

    public Timestamp getaTime() {
        return aTime;
    }

    public void setaTime(Timestamp aTime) {
        this.aTime = aTime;
    }

    public Integer getaWeightInit() {
        return aWeightInit;
    }

    public void setaWeightInit(Integer aWeightInit) {
        this.aWeightInit = aWeightInit;
    }

    public Integer getaWeightNow() {
        return aWeightNow;
    }

    public void setaWeightNow(Integer aWeightNow) {
        this.aWeightNow = aWeightNow;
    }
}
