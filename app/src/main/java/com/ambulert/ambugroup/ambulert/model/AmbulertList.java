package com.ambulert.ambugroup.ambulert.model;

public class AmbulertList {

    private int listIcon;
    private String listTitle;

    public AmbulertList(int listIcon, String listTitle) {
        this.listIcon = listIcon;
        this.listTitle = listTitle;
    }

    public int getListIcon() {
        return listIcon;
    }

    public void setListIcon(int listIcon) {
        this.listIcon = listIcon;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }
}
