package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReportResponse {

    @SerializedName("add_report")
    @Expose
    private String add_report;
    @SerializedName("message")
    @Expose
    private String message;

    public UserReportResponse(String add_report, String message) {
        this.add_report = add_report;
        this.message = message;
    }

    public String getAdd_report() {
        return add_report;
    }

    public void setAdd_report(String add_report) {
        this.add_report = add_report;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
