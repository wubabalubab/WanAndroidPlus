package com.example.kotlintestdemo.mvp.model;

import com.example.kotlintestdemo.bean.JRBean.BannerBean;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.HoChild1FgMvp;
import com.example.kotlintestdemo.net.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

public class HoChild1FgModle implements HoChild1FgMvp.Model {
    @Override
    public Observable<BaseObjectBean<data>> homeData(int page) {
        return RetrofitClient.getInstance().getAPIService().homeData(page);
    }

    @Override
    public Observable<BaseObjectBean<List<BannerBean>>> BannerData() {
        return RetrofitClient.getInstance().getAPIService().BannerData();
    }

    @Override
    public Observable<BaseObjectBean<List<data.DatasBean>>> getTopArticle() {
        return RetrofitClient.getInstance().getAPIService().getTopArticle();
    }
}
