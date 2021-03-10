package com.example.kotlintestdemo.mvp.presenter;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.HoChild1FgMvp;
import com.example.kotlintestdemo.mvp.model.HoChild1FgModle;
import com.example.kotlintestdemo.net.RxSuheduler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HoChild1FgPresenter extends BaseMvpPresenter<HoChild1FgMvp.View>
        implements HoChild1FgMvp.presenter {
    private HoChild1FgMvp.Model model;

    public HoChild1FgPresenter() {
        this.model = new HoChild1FgModle();
    }

    @Override
    public void homeData(int page) {
        model.homeData(page).compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showloading();
                    }

                    @Override
                    public void onNext(BaseObjectBean<data> bean) {
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
