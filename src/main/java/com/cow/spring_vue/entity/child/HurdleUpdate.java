package com.cow.spring_vue.entity.child;

public class HurdleUpdate {
    private String hName;

    private String hDesc;

    private Integer hMax;

    private Integer hSaved;

    private String hEnable;

    private String fhId;

    private String hId;

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

    public String gethEnable() {
        return hEnable;
    }

    public void sethEnable(String hEnable) {
        this.hEnable = hEnable;
    }

    public String getFhId() {
        return fhId;
    }

    public void setFhId(String fhId) {
        this.fhId = fhId;
    }
}
