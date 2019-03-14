package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class SignInUser {

    @SerializedName("user_email")
    private String user_email;
    @SerializedName("user_password")
    private String user_password;

    public SignInUser(String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
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
