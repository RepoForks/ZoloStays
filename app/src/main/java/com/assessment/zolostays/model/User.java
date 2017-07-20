package com.assessment.zolostays.model;

import java.io.Serializable;

/**
 * Created by DELL on 20-07-2017.
 */

public class User implements Serializable{

    public String id;
    public String phone;
    public String email;
    public String name;
    public String password;

    public User(String id, String phone, String email, String name, String password) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String phone, String email, String name, String password) {
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
