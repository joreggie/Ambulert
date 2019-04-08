package com.ambulert.ambugroup.ambulert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HospitalNameResponse {

    @SerializedName("hospitals")
    @Expose
    private ArrayList<ListHospital> listHospital;


    public HospitalNameResponse(ArrayList<ListHospital> listHospital) {
        this.listHospital = listHospital;
    }

    public ArrayList<ListHospital> getListHospital() {
        return listHospital;
    }

    public void setListHospital(ArrayList<ListHospital> listHospital) {
        this.listHospital = listHospital;
    }
}
