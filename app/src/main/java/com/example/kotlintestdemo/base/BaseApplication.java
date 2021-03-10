package com.example.kotlintestdemo.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static BaseApplication Myapp;

    @Override
    public void onCreate() {
        super.onCreate();
        Myapp=this;
    }
    public static BaseApplication getMyapp(){
        return Myapp;
    }
}
