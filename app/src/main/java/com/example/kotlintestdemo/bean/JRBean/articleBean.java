package com.example.kotlintestdemo.bean.JRBean;

public class articleBean {
    private int id;
    private String name;

    public articleBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "articleBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
