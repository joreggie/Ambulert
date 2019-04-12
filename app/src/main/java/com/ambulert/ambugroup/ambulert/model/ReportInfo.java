package com.ambulert.ambugroup.ambulert.model;

public class ReportInfo {

    private String id;
    private String responder_firstname;
    private String responder_middlename;
    private String responder_lastname;
    private String created;
    private ListHospital hospital;
    private reportItem report_info;

    public ReportInfo(String id, String responder_firstname, String responder_middlename, String responder_lastname, String created, ListHospital hospital, reportItem report_info) {
        this.id = id;
        this.responder_firstname = responder_firstname;
        this.responder_middlename = responder_middlename;
        this.responder_lastname = responder_lastname;
        this.created = created;
        this.hospital = hospital;
        this.report_info = report_info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponder_firstname() {
        return responder_firstname;
    }

    public void setResponder_firstname(String responder_firstname) {
        this.responder_firstname = responder_firstname;
    }

    public String getResponder_middlename() {
        return responder_middlename;
    }

    public void setResponder_middlename(String responder_middlename) {
        this.responder_middlename = responder_middlename;
    }

    public String getResponder_lastname() {
        return responder_lastname;
    }

    public void setResponder_lastname(String responder_lastname) {
        this.responder_lastname = responder_lastname;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ListHospital getHospital() {
        return hospital;
    }

    public void setHospital(ListHospital hospital) {
        this.hospital = hospital;
    }

    public reportItem getReport_info() {
        return report_info;
    }

    public void setReport_info(reportItem report_info) {
        this.report_info = report_info;
    }
}
