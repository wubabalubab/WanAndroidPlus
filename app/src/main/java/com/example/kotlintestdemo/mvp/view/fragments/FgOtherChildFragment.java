package com.example.kotlintestdemo.mvp.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.OtherFgContract;
import com.example.kotlintestdemo.mvp.presenter.OhterFgPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FgOtherChildFragment extends BaseMvpFragment<OhterFgPresenter> implements OtherFgContract.View {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String CHILDID = "childId";
    private int mColumnCount = 2;
    private int childId = 0;
    private int page = 0;
    private List<data.DatasBean> dataList=new ArrayList<>();

    @BindView(R.id.rv_fg_otherchild_list)
    RecyclerView recyclerView;
    private static final String TAG = "FgOtherChildFragment";
    private MyItemRecyclerViewAdapter adapter;
    public FgOtherChildFragment() {
    }

    public static FgOtherChildFragment newInstance(int columnCount,int childId) {
        FgOtherChildFragment fragment = new FgOtherChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(CHILDID, childId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            childId=getArguments().getInt(CHILDID);
        }
    }

    @Override
    protected int layoutid() {
        return R.layout.fragment_fg_other_child_list;
    }


    @Override
    protected void initView(View View) {
        mPresent=new OhterFgPresenter();
        mPresent.attachView(FgOtherChildFragment.this);
        mPresent.showProjectList(page,childId);
        Log.e(TAG, "initView: "+page+childId);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        adapter=new MyItemRecyclerViewAdapter(getContext(),dataList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void setProjectClassify(BaseObjectBean<List<TixiBean>> bean) {

    }

    @Override
    public void setProjectList(BaseObjectBean<data> bean) {
        if (bean.getErrorCode()==0) {
            dataList.addAll(bean.getData().getDatas());
            adapter.notifyDataSetChanged();
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

    }
}