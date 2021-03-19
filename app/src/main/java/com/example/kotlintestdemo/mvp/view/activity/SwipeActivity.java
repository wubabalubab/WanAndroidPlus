package com.example.kotlintestdemo.mvp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.pullloadAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SwipeActivity extends AppCompatActivity {

    RecyclerView pullRv;
    pullloadAdapter mAdapter;
    List<String> mlist = new ArrayList<>();
    private static final String TAG = "SwipeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        for (int i = 0; i < 12; i++) {
            mlist.add("asdf"+i);
        }
        final RefreshLayout refeshlayout = findViewById(R.id.refreshlayout);
//        refeshlayout.setRefreshHeader(new ClassicsHeader(this));
//        refeshlayout.setRefreshFooter(new ClassicsFooter(this));
        refeshlayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                for (int i = 0; i < 12; i++) {
                    mlist.add("loadmore "+i);
                }
                mAdapter.notifyDataSetChanged();
                refeshlayout.finishLoadMore(1000,true,false);
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mlist.clear();
                for (int i = 0; i < 12; i++) {
                    mlist.add("refresh "+i);
                }
                mAdapter.notifyDataSetChanged();
                refeshlayout.finishRefresh(1000,true,false);
            }
        });

        pullRv = findViewById(R.id.rvrefresh);
        LinearLayoutManager manager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        pullRv.setLayoutManager(manager);

        Log.d(TAG, "onCreate: "+mlist.size());
        mAdapter=new pullloadAdapter(this,mlist);
        pullRv.setAdapter(mAdapter);
    }
}
