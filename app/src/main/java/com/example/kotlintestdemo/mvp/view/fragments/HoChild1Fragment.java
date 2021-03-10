package com.example.kotlintestdemo.mvp.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.recyadapter.HoChild1FgRecyAdapter;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.HoChild1FgMvp;
import com.example.kotlintestdemo.mvp.presenter.HoChild1FgPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 文章 列表
 * Use the {@link HoChild1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HoChild1Fragment extends BaseMvpFragment<HoChild1FgPresenter> implements HoChild1FgMvp.View {
    private static final String TAG = "HoChild1Fragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_fghochild_main)
    RecyclerView rvFghochildMain;
    @BindView(R.id.fg_hochild_swiperefresh)
    SwipeRefreshLayout fgHochildSwiperefresh;

    private HoChild1FgRecyAdapter adapter;
    private List<data.DatasBean> list;
    private String mParam1;
    private String mParam2;
    private int page = 0;

    public HoChild1Fragment() {
    }

    public static HoChild1Fragment newInstance(String param1, String param2) {
        HoChild1Fragment fragment = new HoChild1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View View) {
        list = new ArrayList<>();
        mPresent = new HoChild1FgPresenter();
        mPresent.attachView(HoChild1Fragment.this);
        mPresent.homeData(page);


        adapter = new HoChild1FgRecyAdapter(list);
        adapter.getLoadMoreModule();
        rvFghochildMain.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFghochildMain.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        fgHochildSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                fgHochildSwiperefresh.setRefreshing(false);
                mPresent.homeData(0);
            }
        });

        adapter.getLoadMoreModule().setAutoLoadMore(true);
        adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                adapter.getLoadMoreModule().isLoading();
                mPresent.homeData(page++);

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int layoutid() {
        return R.layout.fragment_ho_child1;
    }

    @Override
    public void success(BaseObjectBean<data> bean) {

        list.addAll(bean.getData().getDatas());
        adapter.notifyDataSetChanged();
            fgHochildSwiperefresh.setRefreshing(false);
        if (bean.getData().getDatas().size()>=19) {
        adapter.getLoadMoreModule().loadMoreComplete();
        } else {
            adapter.getLoadMoreModule().loadMoreEnd();
        }
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {
        adapter.getLoadMoreModule().loadMoreFail();

    }
}
