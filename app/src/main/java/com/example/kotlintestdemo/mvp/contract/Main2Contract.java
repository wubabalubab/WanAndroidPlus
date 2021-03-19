package com.example.kotlintestdemo.mvp.contract;

import com.example.kotlintestdemo.base.BaseView;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;

import io.reactivex.Observable;

public interface Main2Contract {
    interface Model{
        Observable<BaseObjectBean<data>> homeData(int page);
    }
    interface presenter{
        void homeData(int page);
    }
    interface View extends BaseView{
        void success(BaseObjectBean<data> bean);
        @Override
        void showloading();

        @Override
        void hideLoading();

        @Override
        void onError(String message);
    }
}
