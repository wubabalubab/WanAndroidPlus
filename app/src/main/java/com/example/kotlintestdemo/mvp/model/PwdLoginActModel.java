package com.example.kotlintestdemo.mvp.model;

import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.UserBean;
import com.example.kotlintestdemo.mvp.contract.PwdLoginActContract;
import com.example.kotlintestdemo.net.RetrofitClient;

import io.reactivex.Observable;

public class PwdLoginActModel implements PwdLoginActContract.Model {
    @Override
    public Observable<BaseObjectBean<UserBean>> getLogin(String username, String password) {
        return RetrofitClient.getInstance().getAPIService().getLogin(username, password);
    }

    @Override
    public Observable<BaseObjectBean<UserBean>> getRegister(String username, String password, String repassword) {
        return RetrofitClient.getInstance().getAPIService().getRegister(username, password, repassword);
    }

    @Override
    public Observable<BaseObjectBean<String>> getLogout() {
        return RetrofitClient.getInstance().getAPIService().getloginout();
    }
}
