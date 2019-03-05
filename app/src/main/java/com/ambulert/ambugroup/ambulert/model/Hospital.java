package com.ambulert.ambugroup.ambulert.model;

public class Hospital {
    private int hospitalIcon;
    private String hospitalName;
    private String hospitalLocation;
    private String hospitalType;

    public Hospital(int hospitalIcon, String hospitalName, String hospitalLocation, String hospitalType) {
        this.hospitalIcon = hospitalIcon;
        this.hospitalName = hospitalName;
        this.hospitalLocation = hospitalLocation;
        this.hospitalType = hospitalType;
    }

    public int getHospitalIcon() {
        return hospitalIcon;
    }

    public void setHospitalIcon(int hospitalIcon) {
        this.hospitalIcon = hospitalIcon;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalLocation() {
        return hospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }
}
