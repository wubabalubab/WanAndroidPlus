package com.example.kotlintestdemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class HomeFgVPAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragments;

    public HomeFgVPAdapter(Context context,@NonNull FragmentManager fm,List<Fragment> list, int behavior) {
        super(fm, behavior);
        this.context=context;
        this.fragments=list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
//        Fragment fragment= (Fragment) super.instantiateItem(container, position);
    }

    String[] titles ={"tab1","tab2"};
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position];
//    }

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
