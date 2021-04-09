package com.example.kotlintestdemo.mvp.presenter;

import com.example.kotlintestdemo.base.BaseMvpPresenter;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.OtherFgContract;
import com.example.kotlintestdemo.mvp.model.OtherFgModel;
import com.example.kotlintestdemo.net.RxSuheduler;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class OhterFgPresenter extends BaseMvpPresenter<OtherFgContract.View>
        implements OtherFgContract.Presenter {
    private OtherFgModel model;

    public OhterFgPresenter() {
        this.model = new OtherFgModel();
    }


    @Override
    public void showProjectClassify() {
        model.getProjectClassify().compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<List<TixiBean>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showloading();
                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<List<TixiBean>> dataBaseObjectBean) {
                        mView.setProjectClassify(dataBaseObjectBean);
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
    public void showProjectList(int page, int cid) {
        model.getProjectList(page, cid).compose(RxSuheduler.Obs_io_main())
                .subscribe(new Observer<BaseObjectBean<data>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<data> dataBaseObjectBean) {
                        mView.setProjectList(dataBaseObjectBean);
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
