package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListHospital {

    @SerializedName("hospital_name")
    @Expose
    private String hospital_name;
    @SerializedName("hospital_address")
    @Expose
    private String hospital_address;
    private String hospital_email;
    private String hospital_contact;
    private String hospital_type;
    private String created;

    public ListHospital(String hospital_name, String hospital_address, String hospital_email, String hospital_contact, String hospital_type, String created) {
        this.hospital_name = hospital_name;
        this.hospital_address = hospital_address;
        this.hospital_email = hospital_email;
        this.hospital_contact = hospital_contact;
        this.hospital_type = hospital_type;
        this.created = created;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public String getHospital_email() {
        return hospital_email;
    }

    public String getHospital_contact() {
        return hospital_contact;
    }

    public String getHospital_type() {
        return hospital_type;
    }

    public String getCreated() {
        return created;
    }
}
