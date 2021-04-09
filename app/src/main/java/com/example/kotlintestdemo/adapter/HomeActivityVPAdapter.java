package com.example.kotlintestdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.kotlintestdemo.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class HomeActivityVPAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    Context context;

    public HomeActivityVPAdapter(Context context, @NonNull FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
        this.context = context;
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

    public View getTabView(int position, TabLayout tabLayout) {
        View view = LayoutInflater.from(context).inflate(R.layout.bg_actmain_tablayout, tabLayout, false);
        ImageView imageView = view.findViewById(R.id.im_bg_actmain_tablayout);
        switch (position) {
            case 0:
                imageView.setBackgroundResource(R.drawable.ic_action_home);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.ic_action_project);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.ic_action_other);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.ic_action_persion);
                break;
        }
        return view;
    }
}
