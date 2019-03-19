package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class AlertHospital {

    @SerializedName("emergencyLocation")
    private String emergencyLocation;
    @SerializedName("emergencyType")
    private String emergencyType;
    @SerializedName("others")
    private String others;

}
