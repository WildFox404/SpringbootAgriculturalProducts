package com.cow.spring_vue.entity.child;

import com.cow.spring_vue.entity.Animal;

import java.sql.Timestamp;
import java.util.List;

public class HurdleWithAnimalsDTO {
    private String hId;
    private String hName;
    private String hDesc;
    private int hMax;
    private int hSaved;
    private Timestamp hCreateTime;
    private Timestamp hUpdateTime;
    private String hEnable;
    private String hFull;
    private String fhId;
    private List<Animal> animalData;

    // Getters and Setters

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

    public int gethMax() {
        return hMax;
    }

    public void sethMax(int hMax) {
        this.hMax = hMax;
    }

    public int gethSaved() {
        return hSaved;
    }

    public void sethSaved(int hSaved) {
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

    public List<Animal> getAnimalData() {
        return animalData;
    }

    public void setAnimalData(List<Animal> animalData) {
        this.animalData = animalData;
    }
}