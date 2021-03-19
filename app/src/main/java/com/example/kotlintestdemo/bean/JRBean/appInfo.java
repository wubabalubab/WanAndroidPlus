package com.example.kotlintestdemo.bean.JRBean;

import android.graphics.drawable.Drawable;

public class appInfo {
    String appPKname,applaunchname,appname;
    Drawable drawable;

    public String getAppPKname() {
        return appPKname;
    }

    public void setAppPKname(String appPKname) {
        this.appPKname = appPKname;
    }

    public String getApplaunchname() {
        return applaunchname;
    }

    public void setApplaunchname(String applaunchname) {
        this.applaunchname = applaunchname;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
