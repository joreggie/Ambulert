package com.ambulert.ambugroup.ambulert.model;

public class Report  {

    private String report_title;
    private String report_type;
    private int report_image;
    private String report_location;
    private Responders report_responders;
    private String report_time;
    private String report_date;

    public Report(String report_title, String report_type, int report_image, String report_location, Responders report_responders, String report_time, String report_date) {
        this.report_title = report_title;
        this.report_type = report_type;
        this.report_image = report_image;
        this.report_location = report_location;
        this.report_responders = report_responders;
        this.report_time = report_time;
        this.report_date = report_date;
    }

    public String getReport_title() {
        return report_title;
    }

    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public int getReport_image() {
        return report_image;
    }

    public void setReport_image(int report_image) {
        this.report_image = report_image;
    }

    public String getReport_location() {
        return report_location;
    }

    public void setReport_location(String report_location) {
        this.report_location = report_location;
    }

    public Responders getReport_responders() {
        return report_responders;
    }

    public void setReport_responders(Responders report_responders) {
        this.report_responders = report_responders;
    }

    public String getReport_time() {
        return report_time;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }
}

