package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.SerializedName;

public class SignInResponder {

    @SerializedName("responder_id")
    private String responder_id;

    public SignInResponder(String responder_id) {
        this.responder_id = responder_id;
    }

    public String getResponder_id() {
        return responder_id;
    }

    public void setResponder_id(String responder_id) {
        this.responder_id = responder_id;
    }
}
