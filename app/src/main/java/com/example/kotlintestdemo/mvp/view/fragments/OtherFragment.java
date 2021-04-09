package com.example.kotlintestdemo.mvp.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.FgOtherViewPagerAdapter;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.OtherFgContract;
import com.example.kotlintestdemo.mvp.presenter.OhterFgPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class OtherFragment extends BaseMvpFragment<OhterFgPresenter> implements OtherFgContract.View {
    private static final String TAG = "OtherFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.tab_fg_other)
    TabLayout tabLayout;
    @BindView(R.id.vp_fg_other)
    ViewPager viewPager;
    private String mParam1;
    private String mParam2;
    private FgOtherViewPagerAdapter vpAdapter;
    private List<TixiBean> tixiBeans = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

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
        vpAdapter = new FgOtherViewPagerAdapter(getContext(),fragments,getParentFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(vpAdapter);


        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setProjectClassify(BaseObjectBean<List<TixiBean>> bean) {
        if (bean.getErrorCode() == 0) {
            tixiBeans.addAll(bean.getData());
            List<String> list = new ArrayList<>();
            for (int i = 0; i < tixiBeans.size(); i++) {
                if (!TextUtils.isEmpty(tixiBeans.get(i).getName())) {
                    list.add(tixiBeans.get(i).getName());
                }
                Log.e(TAG, "setProjectClassify: create" );
                fragments.add(FgOtherChildFragment.newInstance(2,tixiBeans.get(i).getId()));
            }
            vpAdapter.notifyDataSetChanged();
            vpAdapter.addTitles(tixiBeans);
            for (int i = 0; i < list.size(); i++) {
                tabLayout.addTab(tabLayout.newTab());
                TabLayout.Tab tab=tabLayout.getTabAt(i);
                tab.setCustomView(vpAdapter.getTabView(i, tabLayout));
            }
        }
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
