package com.ambulert.ambugroup.ambulert.model;

public class Setting {

    private int settingIcon;
    private String settingTitle;

    public Setting(int settingIcon, String settingTitle) {
        this.settingIcon = settingIcon;
        this.settingTitle = settingTitle;
    }

    public int getSettingIcon() {
        return settingIcon;
    }

    public void setSettingIcon(int settingIcon) {
        this.settingIcon = settingIcon;
    }

    public String getSettingTitle() {
        return settingTitle;
    }

    public void setSettingTitle(String settingTitle) {
        this.settingTitle = settingTitle;
    }
}
