package com.ambulert.ambugroup.ambulert.model;

public class ProfileResponse {
    String message;
    String profile;

    public ProfileResponse(String message, String profile) {
        this.message = message;
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
