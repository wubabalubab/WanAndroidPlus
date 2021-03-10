package com.example.kotlintestdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

import com.example.kotlintestdemo.base.BaseApplication;

public class SystemUtil {
    public static boolean isNetConnected(){
        ConnectivityManager connectivityManager= (ConnectivityManager) BaseApplication.getMyapp()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return connectivityManager.getActiveNetwork()!=null;
        }else {
            return connectivityManager.getActiveNetworkInfo().isConnected();
        }
    }
}
