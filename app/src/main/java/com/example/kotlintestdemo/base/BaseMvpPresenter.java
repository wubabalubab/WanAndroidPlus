package com.example.kotlintestdemo.base;

public class BaseMvpPresenter<V extends BaseView> {
    protected V mView;

    public void attachView(V view){
        this.mView = view;
    }
    public void detachView(){
        this.mView = null;
    }
    public boolean isViewAttached(){
        return this.mView != null;
    }
}
