package com.example.kotlintestdemo.mvp.model;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.ProjectFgContract;
import com.example.kotlintestdemo.net.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

public class ProjectFgModel implements ProjectFgContract.Model {
    @Override
    public Observable<BaseObjectBean<List<TixiBean>>> getTixiList() {
        return RetrofitClient.getInstance().getAPIService().getTixiList();
    }

    @Override
    public Observable<BaseObjectBean<data>> getData(int page,int cid) {
        return RetrofitClient.getInstance().getAPIService().getArticleList(page,cid);
    }
}
