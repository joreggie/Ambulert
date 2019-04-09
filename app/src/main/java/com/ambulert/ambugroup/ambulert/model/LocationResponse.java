package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationResponse {

    @SerializedName("report")
    @Expose
    private ArrayList<Report> listReports;

    public LocationResponse(ArrayList<Report> listReports) {
        this.listReports = listReports;
    }

    public ArrayList<Report> getListReports() {
        return listReports;
    }
}
