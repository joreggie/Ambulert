package com.ambulert.ambugroup.ambulert.model;

public class SignUpResponse {

    private String signup;
    private String message;

    public SignUpResponse(String signup, String message) {
        this.signup = signup;
        this.message = message;
    }

    public String getSignup() {
        return signup;
    }

    public void setSignup(String signup) {
        this.signup = signup;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
