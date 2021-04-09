package com.example.kotlintestdemo.util;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class MyTablayout extends TabLayout {
    public MyTablayout(@NonNull Context context) {
        super(context);
    }

    public MyTablayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTablayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWidth();
    }

    private void initWidth() {

    }
}
