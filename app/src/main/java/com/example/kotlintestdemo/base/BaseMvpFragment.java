package com.example.kotlintestdemo.base;

public abstract class BaseMvpFragment<T extends BaseMvpPresenter> extends BaseFragment implements BaseView{

    protected T mPresent;
    @Override
    public void onDestroyView() {
        if (mPresent != null) {
            mPresent.detachView();
        }
        super.onDestroyView();
    }

    // FIXME: 20/12/2020 MVP导致内存泄露
}
