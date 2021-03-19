package com.example.kotlintestdemo.mvp.model;

import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.Main2Contract;
import com.example.kotlintestdemo.net.RetrofitClient;

import io.reactivex.Observable;

public class Main2Model implements Main2Contract.Model {
    @Override
    public Observable<BaseObjectBean<data>> homeData(int page) {
        return RetrofitClient.getInstance().getAPIService()
                .homeData(page);
    }
}
