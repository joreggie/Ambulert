package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class UserId {

    @SerializedName("userid")
    private String userid;

    public UserId(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }
}
