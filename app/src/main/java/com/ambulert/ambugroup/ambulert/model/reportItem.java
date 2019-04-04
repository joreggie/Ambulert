package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class reportItem {

    private String report_location;
    private String report_type;
    @SerializedName("created")
    private String report_created;

    public reportItem(String report_location, String report_type, String report_created) {
        this.report_location = report_location;
        this.report_type = report_type;
        this.report_created = report_created;
    }

    public String getReport_location() {
        return report_location;
    }

    public void setReport_location(String report_location) {
        this.report_location = report_location;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getReport_created() {
        return report_created;
    }

    public void setReport_created(String report_created) {
        this.report_created = report_created;
    }
}
