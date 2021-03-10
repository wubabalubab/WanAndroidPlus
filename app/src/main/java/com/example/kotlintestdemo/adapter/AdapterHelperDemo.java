package com.example.kotlintestdemo.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.bean.articleBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdapterHelperDemo extends BaseQuickAdapter<articleBean, BaseViewHolder> {

    public AdapterHelperDemo(int layoutResId, @Nullable List<articleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, articleBean articleBean) {
            baseViewHolder.setText(R.id.tv_item_adapterhelper, articleBean.getId()+"")
                    .setText(R.id.tv_item_adapterhelpername,articleBean.getName()+"")
                        .setVisible(R.id.tv_item_adapterhelper, true);
        TextView textView= baseViewHolder.getView(R.id.tv_item_adapterhelpername);
        int possition =baseViewHolder.getAdapterPosition();


    }
}
