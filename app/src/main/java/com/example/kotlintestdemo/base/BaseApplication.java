package com.example.kotlintestdemo.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

public class BaseApplication extends Application {
    public static BaseApplication Myapp;
    private static final String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Myapp=this;

        ActivityManager activityManager= (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Log.e(TAG, "onCreate: "+activityManager.getMemoryClass());
    }
    public static BaseApplication getMyapp(){
        return Myapp;
    }
}
