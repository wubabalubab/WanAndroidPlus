package com.example.kotlintestdemo.mvp.presenter;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.UserBean;
import com.example.kotlintestdemo.mvp.contract.PwdLoginActContract;
import com.example.kotlintestdemo.mvp.model.PwdLoginActModel;
import com.example.kotlintestdemo.net.RxSuheduler;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class PwdLoginActPresenter extends BaseMvpPresenter<PwdLoginActContract.View> implements PwdLoginActContract.CallBack {
    private PwdLoginActModel model;

    public PwdLoginActPresenter() {
        model = new PwdLoginActModel();
    }

    @Override
    public void showLogin(String username, String password) {
        model.getLogin(username, password)
                .compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<UserBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<UserBean> bean) {
                        mView.setLogin(bean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void showRegister(String username, String password, String repassword) {
        model.getRegister(username, password, repassword)
                .compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<UserBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<UserBean> o) {
                        mView.setRegister(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void showLogout() {
        model.getLogout().compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<String> o) {
                        mView.setLogout(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
