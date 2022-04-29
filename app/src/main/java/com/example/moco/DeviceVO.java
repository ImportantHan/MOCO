package com.example.moco;

import android.media.Image;

public class DeviceVO {

    private int adddevice_image;
    private String adddevice_name;

    public DeviceVO(int adddevice_image, String adddevice_name) {
        this.adddevice_image = adddevice_image;
        this.adddevice_name = adddevice_name;
    }

    public int getAdddevice_image() {
        return adddevice_image;
    }

    public void setAdddevice_image(int adddevice_image) {
        this.adddevice_image = adddevice_image;
    }

    public String getAdddevice_name() {
        return adddevice_name;
    }

    public void setAdddevice_name(String adddevice_name) {
        this.adddevice_name = adddevice_name;
    }
}
