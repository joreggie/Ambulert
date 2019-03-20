package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class AlertHospital {

    @SerializedName("emergencyLocation")
    private String emergencyLocation;
    @SerializedName("emergencyType")
    private String emergencyType;
    @SerializedName("others")
    private String others;

    public AlertHospital(String emergencyLocation, String emergencyType, String others) {
        this.emergencyLocation = emergencyLocation;
        this.emergencyType = emergencyType;
        this.others = others;
    }

    public String getEmergencyLocation() {
        return emergencyLocation;
    }

    public void setEmergencyLocation(String emergencyLocation) {
        this.emergencyLocation = emergencyLocation;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
