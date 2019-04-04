package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserIdResponse {
    @SerializedName("user_reports")
    private ArrayList<reportItem> reportItem = null;

    public UserIdResponse(ArrayList<com.ambulert.ambugroup.ambulert.model.reportItem> reportItem) {
        this.reportItem = reportItem;
    }

    public ArrayList<com.ambulert.ambugroup.ambulert.model.reportItem> getReportItem() {
        return reportItem;
    }
}
