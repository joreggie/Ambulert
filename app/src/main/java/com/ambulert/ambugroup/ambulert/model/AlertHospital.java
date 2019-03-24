package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class AlertHospital {

    @SerializedName("userid")
    private String userid;
    @SerializedName("location")
    private String emergencyLocation;
    @SerializedName("emergencyType")
    private String emergencyType;
    @SerializedName("others")
    private String others;

    public AlertHospital(String userid, String emergencyLocation, String emergencyType, String others) {
        this.userid = userid;
        this.emergencyLocation = emergencyLocation;
        this.emergencyType = emergencyType;
        this.others = others;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
