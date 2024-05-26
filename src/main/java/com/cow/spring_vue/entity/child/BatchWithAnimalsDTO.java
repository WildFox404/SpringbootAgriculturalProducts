package com.cow.spring_vue.entity.child;

import com.cow.spring_vue.entity.Animal;

import java.sql.Timestamp;
import java.util.List;

public class BatchWithAnimalsDTO{

    private String bBatchId;

    private Timestamp bTime;

    private String bStatus;

    private String bLocation;

    private String bQuarantine;

    private List<Animal> animalData;

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

    public String getbQuarantine() {
        return bQuarantine;
    }

    public void setbQuarantine(String bQuarantine) {
        this.bQuarantine = bQuarantine;
    }

    public List<Animal> getAnimalData() {
        return animalData;
    }

    public void setAnimalData(List<Animal> animalData) {
        this.animalData = animalData;
    }
}
