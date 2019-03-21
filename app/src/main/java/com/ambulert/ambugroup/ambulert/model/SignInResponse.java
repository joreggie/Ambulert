package com.ambulert.ambugroup.ambulert.model;

public class SignInResponse {
    private String signin;
    private String message;
    private String userid;
    private String user_firstname;
    private String user_middlename;
    private String user_lastname;
    private String user_email;

    public SignInResponse(String signin, String message, String userid, String user_firstname, String user_middlename, String user_lastname, String user_email) {
        this.signin = signin;
        this.message = message;
        this.userid = userid;
        this.user_firstname = user_firstname;
        this.user_middlename = user_middlename;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
