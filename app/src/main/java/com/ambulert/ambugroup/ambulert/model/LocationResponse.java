package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LocationResponse {

    private ReportInfo report_info;
    public LocationResponse(ReportInfo report_info) {
        this.report_info = report_info;
    }

    public ReportInfo getReport_info() {
        return report_info;
    }

    public void setReport_info(ReportInfo report_info) {
        this.report_info = report_info;
    }
}
