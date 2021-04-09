package com.example.kotlintestdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.mvp.view.fragments.FgOtherChildFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FgOtherViewPagerAdapter extends FragmentStatePagerAdapter {

    private int size;
    private Context context;
    private List<Fragment> fragments;
    List<TixiBean> list=new ArrayList<>();
    public FgOtherViewPagerAdapter(Context context,List<Fragment> fragments,@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.context=context;
        this.fragments=fragments;
    }

    public void addTitles(List<TixiBean> list) {
        this.list=list;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
    public View getTabView(int posotion , TabLayout tabLayout){
        View view= LayoutInflater.from(context).inflate(R.layout.item_fgothertab, tabLayout,false);
        TextView textView=view.findViewById(R.id.tv_item_fgothertab);
        textView.setText(list.get(posotion).getName());
        return view;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
