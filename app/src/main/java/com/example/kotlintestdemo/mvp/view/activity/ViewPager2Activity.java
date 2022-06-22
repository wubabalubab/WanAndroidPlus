package com.example.kotlintestdemo.mvp.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseActivity;

import butterknife.ButterKnife;

public class ViewPager2Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_view_pager2;
    }

    @Override
    public void initView() {

    }
}