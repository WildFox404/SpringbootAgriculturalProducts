package com.cow.spring_vue.entity;

import jakarta.validation.constraints.Pattern;

import java.sql.Time;
import java.sql.Timestamp;

public class Hurdle {
    private String hId;

    private String hName;

    private String hDesc;

    private Integer hMax;

    private Integer hSaved;

    private Timestamp hCreateTime;

    private Timestamp hUpdateTime;

    @Pattern(regexp = "可用|禁用")
    private String hEnable;

    private String hFull;

    private String fhId;

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethDesc() {
        return hDesc;
    }

    public void sethDesc(String hDesc) {
        this.hDesc = hDesc;
    }

    public Integer gethMax() {
        return hMax;
    }

    public void sethMax(Integer hMax) {
        this.hMax = hMax;
    }

    public Integer gethSaved() {
        return hSaved;
    }

    public void sethSaved(Integer hSaved) {
        this.hSaved = hSaved;
    }

    public Timestamp gethCreateTime() {
        return hCreateTime;
    }

    public void sethCreateTime(Timestamp hCreateTime) {
        this.hCreateTime = hCreateTime;
    }

    public Timestamp gethUpdateTime() {
        return hUpdateTime;
    }

    public void sethUpdateTime(Timestamp hUpdateTime) {
        this.hUpdateTime = hUpdateTime;
    }

    public String gethEnable() {
        return hEnable;
    }

    public void sethEnable(String hEnable) {
        this.hEnable = hEnable;
    }

    public String gethFull() {
        return hFull;
    }

    public void sethFull(String hFull) {
        this.hFull = hFull;
    }

    public String getFhId() {
        return fhId;
    }

    public void setFhId(String fhId) {
        this.fhId = fhId;
    }
}
