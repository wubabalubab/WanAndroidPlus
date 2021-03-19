package com.example.kotlintestdemo.bean.JRBean;

import java.util.List;

public class loginbean {
    private int id;
    private String password;
    private String email;
    private String username;
    private String icon;
    private String type;
    private List<?> collectedIds;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<?> getCollectedIds() {
        return collectedIds;
    }

    public void setCollectedIds(List<?> collectedIds) {
        this.collectedIds = collectedIds;
    }

    @Override
    public String toString() {
        return "loginbean{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", collectedIds=" + collectedIds +
                '}';
    }
}
