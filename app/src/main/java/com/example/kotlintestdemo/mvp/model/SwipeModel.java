package com.example.kotlintestdemo.mvp.model;

import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.loginbean;
import com.example.kotlintestdemo.mvp.contract.SwipeContract;
import com.example.kotlintestdemo.net.RetrofitClient;

import io.reactivex.Observable;

public class SwipeModel implements SwipeContract.Model {
    @Override
    public Observable<BaseObjectBean<loginbean>> login(String username, String password) {
        return RetrofitClient.getInstance().getAPIService()
                .login(username, password);
    }
}
