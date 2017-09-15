package com.example.eslam.vodafoodies.model;

/**
 * Created by Eslam on 8/25/2017.
 */

public class User {
    String uid;
    String gender;
    String name;
    String email;
    String phone;
    String img;
    String fb_profile;
    public User(String uid, String gender, String name, String email, String phone, String img, String fb_profile) {

        this.uid = uid;
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img = img;
        this.fb_profile = fb_profile;
    }
    public User(String uid, String name, String email, String phone, String img, String fb_profile) {

        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img = img;
        this.fb_profile = fb_profile;
    }
    public User(String name, String email, String phone, String img, String fb_profile) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img = img;
        this.fb_profile = fb_profile;
    }

    public User(String name, String email, String phone, String img) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img = img;
    }

    public User() {

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFb_profile() {
        return fb_profile;
    }

    public void setFb_profile(String fb_profile) {
        this.fb_profile = fb_profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", img='" + img + '\'' +
                ", fb_profile='" + fb_profile + '\'' +
                '}';
    }
}
