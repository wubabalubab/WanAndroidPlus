package com.example.kotlintestdemo.mvp.view.activity;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.HomeActivityVPAdapter;
import com.example.kotlintestdemo.base.BaseMvpActivity;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.Main2Contract;
import com.example.kotlintestdemo.mvp.presenter.Main2Presenter;
import com.example.kotlintestdemo.mvp.view.fragments.HomeFragment;
import com.example.kotlintestdemo.mvp.view.fragments.MineFragment;
import com.example.kotlintestdemo.mvp.view.fragments.OtherFragment;
import com.example.kotlintestdemo.mvp.view.fragments.ProjectFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class Main2Activity extends BaseMvpActivity<Main2Presenter> implements Main2Contract.View {

    private static final String TAG = "Main2Activity";
    BaseObjectBean<data> dataBaseObjectBean;
    List<data.DatasBean> datasList=new ArrayList<>();
    private static final int requestCode = 893;
    @BindView(R.id.vp_actmain)
    ViewPager vpActmain;
    @BindView(R.id.tab_actmain)
    TabLayout tabActmain;

    @Override
    public int layoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(requestCode)
    private void RequestPermission(){
        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, permissions)) {
            EasyPermissions.requestPermissions(this,"kotlinrequest",requestCode,permissions);
        }

    }

    @Override
    public void initView() {
        mPresenter = new Main2Presenter();
//        mPresenter.attachView(this);
//        mPresenter.homeData(0);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("parmer1", "param2"));
        fragments.add(new ProjectFragment());
        fragments.add(new OtherFragment());
        fragments.add(new MineFragment());
        HomeActivityVPAdapter vpAdapter = new HomeActivityVPAdapter(this, getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        vpActmain.setAdapter(vpAdapter);


        vpActmain.setCurrentItem(0);
        tabActmain.setupWithViewPager(vpActmain);

        for (int i = 0; i < tabActmain.getTabCount(); i++) {
            TabLayout.Tab tab = tabActmain.getTabAt(i);
            tab.setCustomView(vpAdapter.getTabView(i, tabActmain));
        }
        tabActmain.setSelectedTabIndicatorHeight(0);
        tabActmain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void success(BaseObjectBean<data> bean) {
        dataBaseObjectBean = bean;
        datasList = dataBaseObjectBean.getData().getDatas();
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
