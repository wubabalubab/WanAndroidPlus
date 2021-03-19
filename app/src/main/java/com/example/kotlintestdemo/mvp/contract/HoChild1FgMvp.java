package com.example.kotlintestdemo.mvp.contract;

import com.example.kotlintestdemo.base.BaseView;
import com.example.kotlintestdemo.bean.JRBean.BannerBean;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;

import java.util.List;

import io.reactivex.Observable;

public interface HoChild1FgMvp {
    interface Model{
        Observable<BaseObjectBean<data>> homeData(int page);
        Observable<BaseObjectBean<List<BannerBean>>> BannerData();
        Observable<BaseObjectBean<List<data.DatasBean>>> getTopArticle();
    }
    interface presenter{
        void homeData(int page);
        void BannerData();
        void getTopArticle();
    }
    interface View extends BaseView{
        void success(BaseObjectBean<data> bean);
        void showBanner(BaseObjectBean<List<BannerBean>> bean);
        void showTopArticle(BaseObjectBean<List<data.DatasBean>> bean);
        @Override
        void showloading();

        @Override
        void hideLoading();

        @Override
        void onError(String message);
    }
}
