package com.ambulert.ambugroup.ambulert.model;

import java.util.ArrayList;

public class HospitalNameResponse {

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
