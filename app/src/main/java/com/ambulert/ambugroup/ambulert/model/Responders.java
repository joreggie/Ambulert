package com.ambulert.ambugroup.ambulert.model;

public class Responders  {

    private String responder_name;
    private String responder_contact;

    public Responders(String responder_name, String responder_contact) {
        this.responder_name = responder_name;
        this.responder_contact = responder_contact;
    }

    public String getResponder_name() {
        return responder_name;
    }

    public void setResponder_name(String responder_name) {
        this.responder_name = responder_name;
    }

    public String getResponder_contact() {
        return responder_contact;
    }

    public void setResponder_contact(String responder_contact) {
        this.responder_contact = responder_contact;
    }


}
