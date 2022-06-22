package com.example.kotlintestdemo.mvp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.FgOtherViewPager2Adapter;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.OtherFgContract;
import com.example.kotlintestdemo.mvp.presenter.OhterFgPresenter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class OtherFragment extends BaseMvpFragment<OhterFgPresenter> implements OtherFgContract.View {
    private static final String TAG = "OtherFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.tab_fg_other)
    TabLayout tabLayout;
    @BindView(R.id.vp2_fg_other)
    ViewPager2 mViewpager2;
    private String mParam1;
    private String mParam2;
    private FgOtherViewPager2Adapter mVpAdapter;
    private List<TixiBean> tixiBeans = new ArrayList<>();
    private List<Integer> listId = new ArrayList<>();
    private TabLayoutMediator tabLayoutMediator;

    public OtherFragment() {
    }

    public static OtherFragment newInstance(String param1, String param2) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return R.layout.fragment_other;
    }

    @Override
    protected void initView(View View) {
        mPresent = new OhterFgPresenter();
        mPresent.attachView(OtherFragment.this);
        mPresent.showProjectClassify();
        mVpAdapter = new FgOtherViewPager2Adapter(getParentFragmentManager(), getLifecycle(), listId);
        mViewpager2.setAdapter(mVpAdapter);
        mViewpager2.setOffscreenPageLimit(3);

    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tabLayoutMediator.detach();
    }

    @Override
    public void setProjectClassify(BaseObjectBean<List<TixiBean>> bean) {
        if (bean.getErrorCode() == 0) {
            tixiBeans.clear();
            listId.clear();
            tixiBeans.addAll(bean.getData());
            for (TixiBean item : bean.getData()) {
                listId.add(item.getId());
            }
            tabLayoutMediator = new TabLayoutMediator(tabLayout, mViewpager2, true, true, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    tab.setCustomView(getTabView(position, tabLayout));
                }
            });
            tabLayoutMediator.attach();
        }
    }

    public View getTabView(int posotion, TabLayout tabLayout) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_fgothertab, tabLayout, false);
        TextView textView = view.findViewById(R.id.tv_item_fgothertab);
        textView.setText(tixiBeans.get(posotion).getName());
        return view;
    }

    @Override
    public void setProjectList(BaseObjectBean<data> bean) {
        if (bean.getErrorCode() == 0) {
            Log.e(TAG, "setProjectClassify: " + bean.getErrorCode());
        }
    }

    @Override
    public void onError(String message) {

    }
}
