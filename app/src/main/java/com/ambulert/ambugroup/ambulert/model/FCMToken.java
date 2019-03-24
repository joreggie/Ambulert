package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class FCMToken {

    @SerializedName("userid")
    private String userid;
    @SerializedName("token")
    private String token;

    public FCMToken(String userid, String token) {
        this.userid = userid;
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
