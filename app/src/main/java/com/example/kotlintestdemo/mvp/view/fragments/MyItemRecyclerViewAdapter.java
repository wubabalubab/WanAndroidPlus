package com.example.kotlintestdemo.mvp.view.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.view.activity.ResoursActivity;
import com.example.kotlintestdemo.util.MyConstant;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<data.DatasBean> mValues;
    private Context context;

    public MyItemRecyclerViewAdapter(Context context,List<data.DatasBean> items) {
        mValues = items;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fg_other_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(context).load(mValues.get(position).getEnvelopePic()).into(holder.imageView);
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getAuthor());
        holder.mClassify.setText(mValues.get(position).getChapterName());

        holder.imGithub.setOnClickListener(v ->{
            context.startActivity(new Intent(context, ResoursActivity.class)
                    .putExtra(MyConstant.CONTENT_URL, mValues.get(position).getProjectLink()));
        });
        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(context, ResoursActivity.class)
                    .putExtra(MyConstant.CONTENT_URL, mValues.get(position).getLink()));
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView imageView,imGithub;
        public final TextView mClassify;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView=view.findViewById(R.id.im_fg_otherchild);
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mClassify=view.findViewById(R.id.tv_fgotherchild_classify);
            imGithub=view.findViewById(R.id.im_fg_otherchild_github);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}