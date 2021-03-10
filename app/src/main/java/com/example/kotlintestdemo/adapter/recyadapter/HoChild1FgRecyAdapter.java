package com.example.kotlintestdemo.adapter.recyadapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.bean.JRBean.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.example.kotlintestdemo.bean.*;

import java.util.List;

public class HoChild1FgRecyAdapter extends BaseQuickAdapter<data.DatasBean,BaseViewHolder> implements LoadMoreModule {
    private static final String TAG = "HoChild1FgRecyAdapter";
    public HoChild1FgRecyAdapter( @Nullable List<data.DatasBean> list) {
        super(R.layout.item_rv_hochildfgrecyadapter, list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, data.DatasBean bean) {
        baseViewHolder.setText(R.id.tv_item_rv_hochildfgad_title, bean.getTitle());
        baseViewHolder.setText(R.id.tv_item_rv_hochildfgad_author2, bean.getAuthor());
        baseViewHolder.setText(R.id.tv_item_rv_hochildfgad_classify2, bean.getSuperChapterName());
        baseViewHolder.setText(R.id.tv_item_rv_hochildfgad_time, bean.getNiceDate());
        Log.e(TAG, bean.toString());
    }
}
