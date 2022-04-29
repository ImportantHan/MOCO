package com.example.moco;

import java.util.HashMap;
import java.util.Map;

public class UserAccount {
    // 사용자 계정 정보 모델 클래스

    private String idToken; // Firebase Uid(고유 토큰정보)
    private String id;
    private String pw;
    private String serial;
    private String phone;
    private boolean fan;
    private boolean light;
    private boolean washingmachine;

    public UserAccount() {
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isFan() {
        return fan;
    }

    public void setFan(boolean fan) {
        this.fan = fan;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public boolean isWashingmachine() {
        return washingmachine;
    }

    public void setWashingmachine(boolean washingmachine) {
        this.washingmachine = washingmachine;
    }
}
