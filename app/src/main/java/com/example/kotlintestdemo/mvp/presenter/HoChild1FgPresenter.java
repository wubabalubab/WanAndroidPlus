package com.example.kotlintestdemo.mvp.presenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.JRBean.BannerBean;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.HoChild1FgMvp;
import com.example.kotlintestdemo.mvp.model.HoChild1FgModle;
import com.example.kotlintestdemo.net.RxSuheduler;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HoChild1FgPresenter extends BaseMvpPresenter<HoChild1FgMvp.View>
        implements HoChild1FgMvp.presenter {
    private HoChild1FgMvp.Model model;

    public HoChild1FgPresenter() {
        this.model = new HoChild1FgModle();
    }

    @Override
    public void BannerData() {
        model.BannerData().compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showloading();
                    }

                    @Override
                    public void onNext(BaseObjectBean<List<BannerBean>> listb) {
                        mView.showBanner(listb);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getTopArticle() {
        model.getTopArticle().compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<List<data.DatasBean>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<List<data.DatasBean>> bean) {
                        mView.showTopArticle(bean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void homeData(int page) {
        model.homeData(page).compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showloading();
                    }

                    // FIXME: 21-4-10 invoke
                    @Override
                    public void onNext( BaseObjectBean<data> bean) {
                        mView.success(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError(e.getMessage());
                        mView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }

}
