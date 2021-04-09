package com.example.kotlintestdemo.mvp.model;

import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.OtherFgContract;
import com.example.kotlintestdemo.net.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class OtherFgModel implements OtherFgContract.Model {

    @Override
    public Observable<BaseObjectBean<List<TixiBean>>> getProjectClassify() {
        return RetrofitClient.getInstance().getAPIService().getProjectClassify();
    }

    @Override
    public Observable<BaseObjectBean<data>> getProjectList(int page, int cid) {
        return RetrofitClient.getInstance().getAPIService().getProjectList(page, cid);
    }
}
