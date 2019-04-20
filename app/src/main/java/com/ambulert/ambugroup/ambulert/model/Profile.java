package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("user_id")
    private String user_id;
    @SerializedName("user_firstname")
    private String user_firstname;
    @SerializedName("user_middlename")
    private String user_middlename;
    @SerializedName("user_lastname")
    private String user_lastname;
    @SerializedName("user_email")
    private String user_email;

    public Profile(String user_id, String user_firstname, String user_middlename, String user_lastname, String user_email) {
        this.user_id = user_id;
        this.user_firstname = user_firstname;
        this.user_middlename = user_middlename;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_middlename() {
        return user_middlename;
    }

    public void setUser_middlename(String user_middlename) {
        this.user_middlename = user_middlename;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
