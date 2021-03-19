package com.example.kotlintestdemo.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.bean.JRBean.appInfo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SharedAdapter extends BaseQuickAdapter<appInfo, BaseViewHolder> {
    private static final String TAG = "SharedAdapter";
    public SharedAdapter( @Nullable List<appInfo> data) {
        super(R.layout.shared_item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, appInfo appInfo) {

        baseViewHolder.setText(R.id.tvshared,appInfo.getAppname() );
        ImageView imageView=baseViewHolder.getView(R.id.imshared);
        Glide.with(getContext()).load(appInfo.getDrawable())
                .into(imageView);
    }
}
