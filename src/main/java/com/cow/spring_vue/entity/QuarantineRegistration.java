package com.cow.spring_vue.entity;

public class QuarantineRegistration {

    private String grId;
    private String bQualified;

    private String grBatchId;

    private String grImg;

    private String grMechanism;

    private String grTime;

    public String getGrId() {
        return grId;
    }

    public void setGrId(String grId) {
        this.grId = grId;
    }

    public String getbQualified() {
        return bQualified;
    }

    public void setbQualified(String bQualified) {
        this.bQualified = bQualified;
    }

    public String getGrBatchId() {
        return grBatchId;
    }

    public void setGrBatchId(String grBatchId) {
        this.grBatchId = grBatchId;
    }

    public String getGrImg() {
        return grImg;
    }

    public void setGrImg(String grImg) {
        this.grImg = grImg;
    }

    public String getGrMechanism() {
        return grMechanism;
    }

    public void setGrMechanism(String grMechanism) {
        this.grMechanism = grMechanism;
    }

    public String getGrTime() {
        return grTime;
    }

    public void setGrTime(String grTime) {
        this.grTime = grTime;
    }
}
