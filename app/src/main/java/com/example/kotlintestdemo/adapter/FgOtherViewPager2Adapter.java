package com.example.kotlintestdemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kotlintestdemo.mvp.view.fragments.FgOtherChildFragment;

import java.util.List;

public class FgOtherViewPager2Adapter extends FragmentStateAdapter {

    List<Integer> listId;
    public FgOtherViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,List<Integer> listId) {
        super(fragmentManager, lifecycle);
        this.listId=listId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return FgOtherChildFragment.newInstance(2, listId.get(position));

    }

    @Override
    public int getItemCount() {
        return listId.size();
    }
}
