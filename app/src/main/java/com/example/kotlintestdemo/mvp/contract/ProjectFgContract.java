package com.example.kotlintestdemo.mvp.contract;

import com.example.kotlintestdemo.base.BaseView;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;

import java.util.List;

import io.reactivex.Observable;

public interface ProjectFgContract {
    interface Model{
        Observable<BaseObjectBean<List<TixiBean>>> getTixiList();
    }
    interface Presenter{
        void showTixiList();
    }
    interface View extends BaseView{
        void setTixiList(BaseObjectBean<List<TixiBean>> bean);
        @Override
        void onError(String message);
    }
}
