package com.ambulert.ambugroup.ambulert.model;

public class SignInResponse {
    private String signin;
    private String message;

    public SignInResponse(String signin, String message) {
        this.signin = signin;
        this.message = message;
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
}
