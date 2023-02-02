package com.example.helperapp2;

public class HelperModel {
    private String uid, userName, email, password, mobile, profession;

    public HelperModel() {
    }

    public HelperModel(String uid, String userName, String email, String password, String mobile, String profession) {
        this.uid = uid;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.profession = profession;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
