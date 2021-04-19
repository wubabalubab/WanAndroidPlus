package com.example.kotlintestdemo.mvp.contract;

import com.example.kotlintestdemo.base.BaseView;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.UserBean;

import io.reactivex.Observable;

public interface PwdLoginActContract {
    interface View extends BaseView{
        void setLogin(BaseObjectBean<UserBean> bean);
        void setRegister(BaseObjectBean<UserBean> bean);
        void setLogout(BaseObjectBean<String> bean);
        @Override
        void onError(String message);
    }
    interface CallBack {
        void showLogin(String username,String password);
        void showRegister(String username,String password,String repassword);
        void showLogout();
    }
    interface Model {
        Observable<BaseObjectBean<UserBean>> getLogin(String username, String password);
        Observable<BaseObjectBean<UserBean>> getRegister(String username,String password,String repassword);
        Observable<BaseObjectBean<String>> getLogout();
    }
}
