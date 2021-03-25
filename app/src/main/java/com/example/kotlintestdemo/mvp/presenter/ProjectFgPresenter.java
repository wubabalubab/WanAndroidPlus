package com.example.kotlintestdemo.mvp.presenter;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.ProjectFgContract;
import com.example.kotlintestdemo.mvp.model.ProjectFgModel;
import com.example.kotlintestdemo.net.RxSuheduler;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProjectFgPresenter extends BaseMvpPresenter<ProjectFgContract.View> implements ProjectFgContract.Presenter{

    private ProjectFgModel model;

    public ProjectFgPresenter() {
        this.model = new ProjectFgModel();
    }
    @Override
    public void showData(int page,int cid) {
        model.getData(page,cid).compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseObjectBean<data> dataBaseObjectBean) {
        mView.setData(dataBaseObjectBean);
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
    public void showTixiList() {
        model.getTixiList().compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<List<TixiBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseObjectBean<List<TixiBean>> listBaseObjectBean) {
                mView.setTixiList(listBaseObjectBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
