package com.example.kotlintestdemo.mvp.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.HomeActivityVPAdapter;
import com.example.kotlintestdemo.adapter.HomeFgVPAdapter;
import com.example.kotlintestdemo.base.BaseFragment;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.HoChild1FgMvp;
import com.example.kotlintestdemo.mvp.presenter.HoChild1FgPresenter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseMvpFragment<HoChild1FgPresenter> implements HoChild1FgMvp.View {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.banner_fghome)
    Banner bannerFghome;
    @BindView(R.id.tab_fghome_top)
    TabLayout tabFghomeTop;
    @BindView(R.id.vp_fghome)
    ViewPager vpFghome;
    HomeFgVPAdapter vpAdapter;
    private List<Fragment> fragments;
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View View) {
        fragments=new ArrayList<>();
        HoChild1Fragment hoChild1Fragment= HoChild1Fragment.newInstance("asdfa", "asdfa");
        HoChild2Fragment hoChild2Fragment= HoChild2Fragment.newInstance("asdfa", "asdfa");
//        fragments.add(hoChild1Fragment );
//        fragments.add(hoChild2Fragment );


        vpAdapter=new HomeFgVPAdapter(getContext(),getChildFragmentManager(),
                fragments, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpFghome.setAdapter(vpAdapter);
        tabFghomeTop.setupWithViewPager(vpFghome);
        vpFghome.setCurrentItem(0);
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<fragments.size();i++) {
            TabLayout.Tab tab=tabFghomeTop.newTab();
            tab.setText(i+"");
            tabFghomeTop.addTab(tab,i);
        }

    }

    @Override
    public void success(BaseObjectBean<data> bean) {

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
