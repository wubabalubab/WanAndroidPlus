package com.example.kotlintestdemo.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

@SuppressLint("Registered")
public abstract class BaseMvpActivity<T extends BaseMvpPresenter> extends BaseActivity implements BaseView{

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    /**
     *  mvp会导致内存泄露
     */
    // FIXME: 20/12/2020
}
