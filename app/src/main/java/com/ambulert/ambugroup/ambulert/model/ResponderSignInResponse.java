package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponderSignInResponse {

    @SerializedName("signin")
    @Expose
    private String signin;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("responder_id")
    @Expose
    private String responder_id;
    @SerializedName("responder_firstname")
    @Expose
    private String responder_firstname;
    @SerializedName("responder_middlename")
    @Expose
    private String responder_middlename;
    @SerializedName("responder_lastname")
    @Expose
    private String responder_lastname;

    public ResponderSignInResponse(String signin, String message, String responder_firstname, String responder_middlename, String responder_lastname) {
        this.signin = signin;
        this.message = message;
        this.responder_firstname = responder_firstname;
        this.responder_middlename = responder_middlename;
        this.responder_lastname = responder_lastname;
    }

    public String getResponder_id() {
        return responder_id;
    }

    public void setResponder_id(String responder_id) {
        this.responder_id = responder_id;
    }

    public String getSignin() {
        return signin;
    }

    public void setSignin(String signin) {
        this.signin = signin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
