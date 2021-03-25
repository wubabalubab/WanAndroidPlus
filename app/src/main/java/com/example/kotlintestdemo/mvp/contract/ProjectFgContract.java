package com.example.kotlintestdemo.mvp.contract;

import com.example.kotlintestdemo.base.BaseView;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;

import java.util.List;

import io.reactivex.Observable;

public interface ProjectFgContract {
    interface Model{
        Observable<BaseObjectBean<List<TixiBean>>> getTixiList();
        Observable<BaseObjectBean<data>> getData(int page,int cil);
    }
    interface Presenter{
        void showTixiList();
        void showData(int page,int cil);
    }
    interface View extends BaseView{
        void setTixiList(BaseObjectBean<List<TixiBean>> bean);
        void setData(BaseObjectBean<data> bean);
        @Override
        void onError(String message);
    }
}
