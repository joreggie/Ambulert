package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class SignUpUser {

    @SerializedName("user_firstname")
    private String user_firstname;
    @SerializedName("user_middlename")
    private String user_middlename;
    @SerializedName("user_lastname")
    private String user_lastname;
    @SerializedName("user_email")
    private String user_email;
    @SerializedName("user_password")
    private String user_password;


    public SignUpUser(String user_firstname, String user_middlename, String user_lastname, String user_email, String user_password) {
        this.user_firstname = user_firstname;
        this.user_middlename = user_middlename;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
        this.user_password = user_password;
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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
