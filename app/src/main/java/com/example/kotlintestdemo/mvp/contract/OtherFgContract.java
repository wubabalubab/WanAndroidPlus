package com.example.kotlintestdemo.mvp.contract;

import com.example.kotlintestdemo.base.BaseView;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface OtherFgContract  {
    interface View extends BaseView{
        void setProjectClassify(BaseObjectBean<List<TixiBean>> bean);
        void setProjectList(BaseObjectBean<data> bean);

        @Override
        void onError(String message);
    }
    interface Presenter {
        void showProjectClassify();
        void showProjectList(int page,int cid);
    }

    interface Model {
        Observable<BaseObjectBean<List<TixiBean>>> getProjectClassify();
        Observable<BaseObjectBean<data>> getProjectList(int page,int cid);
    }
}
