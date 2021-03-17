package com.example.kotlintestdemo.mvp.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.HomeActivityVPAdapter;
import com.example.kotlintestdemo.adapter.HomeFgVPAdapter;
import com.example.kotlintestdemo.adapter.recyadapter.HoChild1FgRecyAdapter;
import com.example.kotlintestdemo.base.BaseFragment;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.BannerBean;
import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.HoChild1FgMvp;
import com.example.kotlintestdemo.mvp.presenter.HoChild1FgPresenter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseMvpFragment<HoChild1FgPresenter> implements HoChild1FgMvp.View , OnBannerListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "HomeFragment";
    @BindView(R.id.banner_fghome)
    Banner bannerFghome;
    @BindView(R.id.rv_fghochild_main)
    RecyclerView rvFghochildMain;
    @BindView(R.id.fg_hochild_swiperefresh)
    SwipeRefreshLayout fgHochildSwiperefresh;
    private String mParam1;
    private String mParam2;

    private HoChild1FgRecyAdapter adapter;
    private List<data.DatasBean> list;
    private int page = 0;
    private List<String> imageurls=new ArrayList<>();
    private List<String> bannerTitles=new ArrayList<>();

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

        list = new ArrayList<>();
        mPresent = new HoChild1FgPresenter();
        mPresent.attachView(HomeFragment.this);
        mPresent.homeData(page);
        mPresent.BannerData();
        bannerFghome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

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
    public void success(BaseObjectBean<data> bean) {
        Log.e(TAG, "success: "+bean.getData().getSize() );
        list.addAll(bean.getData().getDatas());
        adapter.notifyDataSetChanged();
        fgHochildSwiperefresh.setRefreshing(false);
        if (bean.getData().getDatas().size()>0) {
            adapter.getLoadMoreModule().loadMoreComplete();
        } else {
            adapter.getLoadMoreModule().loadMoreEnd();
        }
    }

    @Override
    public void showBanner(BaseObjectBean<List<BannerBean>> bean) {
        for (int i = 0; i < bean.getData().size(); i++) {
            imageurls.add(bean.getData().get(i).getImagePath());
            bannerTitles.add(bean.getData().get(i).getDesc());
        }

        bannerFghome.setImageLoader(new Myload());
        bannerFghome.setImages(imageurls);
        bannerFghome.setBannerTitles(bannerTitles);
        bannerFghome.setDelayTime(3000);
        bannerFghome.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        Log.e(TAG, "OnBannerClick: "+position);
    }

    private class Myload extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            if (null!=getContext()) {
                Glide.with(getContext()).load(path.toString()).into(imageView);
            }
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
