package com.example.kotlintestdemo.mvp.contract;

import com.example.kotlintestdemo.base.BaseView;
import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.loginbean;

import io.reactivex.Observable;

public interface SwipeContract {
    interface Model {
        Observable<BaseObjectBean<loginbean>> login(String username, String password);
    }

    interface presenter {
        void login(String username, String password);
    }

    interface View extends BaseView {
        @Override
        void showloading();

        @Override
        void hideLoading();

        @Override
        void onError(String message);

        void onSuccess(BaseObjectBean<loginbean> bean);
    }
}
