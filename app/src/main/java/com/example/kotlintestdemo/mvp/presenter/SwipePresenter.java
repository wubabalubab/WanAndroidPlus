package com.example.kotlintestdemo.mvp.presenter;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.loginbean;
import com.example.kotlintestdemo.mvp.contract.SwipeContract;
import com.example.kotlintestdemo.mvp.model.SwipeModel;
import com.example.kotlintestdemo.net.RxSuheduler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SwipePresenter extends BaseMvpPresenter<SwipeContract.View>
        implements SwipeContract.presenter {

    private SwipeContract.Model model;

    public SwipePresenter() {
        model = new SwipeModel();
    }

    @Override
    public void login(String username, String password) {
        if (!isViewAttached()) {
            return;
        }
        model.login(username, password)
                .compose(RxSuheduler.<BaseObjectBean<loginbean>>Obs_io_main())

                .subscribe(new Observer<BaseObjectBean<loginbean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showloading();
                    }

                    @Override
                    public void onNext(BaseObjectBean<loginbean> loginbeanBaseObjectBean) {
                        mView.onSuccess(loginbeanBaseObjectBean);
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
