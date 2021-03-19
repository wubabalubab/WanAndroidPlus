package com.example.kotlintestdemo.mvp.presenter;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.Main2Contract;
import com.example.kotlintestdemo.mvp.model.Main2Model;
import com.example.kotlintestdemo.net.RxSuheduler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Main2Presenter extends BaseMvpPresenter<Main2Contract.View>
        implements Main2Contract.presenter {
    private Main2Contract.Model model;

    public Main2Presenter() {
        this.model = new Main2Model();
    }

    @Override
    public void homeData(int page) {
        model.homeData(page).compose(RxSuheduler.<BaseObjectBean<data>>Obs_io_main())
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
