package com.example.kotlintestdemo.mvp.view.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.kotlintestdemo.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "DeleteActivity";
    RecyclerView recyclerView;
    MAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<String> mlist;
    private Pager pager = new Pager();
    private int num;
    private Handler mHandler = new Handler();
    private Runnable mRefresh = new Runnable() {
        @Override
        public void run() {
            swipeRefreshLayout.setRefreshing(false);
            num++;
            for (int i = 0; i < 5; i++) {
                mlist.add(num + " 刷新" + i);
            }
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initData();
        initView();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_delete);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MAdapter(R.layout.item_delete_layout, mlist);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        mlist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mlist.add(String.valueOf(i));
        }
    }

    private void initEvent() {
        adapter.getLoadMoreModule();
        adapter.getLoadMoreModule().setEnableLoadMore(true);
        adapter.getLoadMoreModule().setAutoLoadMore(true);
        adapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(true);
        adapter.getLoadMoreModule().setEnableLoadMoreEndClick(true);
        adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                request();
            }
        });
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
                Log.e(TAG, "onItemSwipeStart: ");
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
                Log.e(TAG, "clearView: ");
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.e(TAG, "onItemSwiped: ");
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(DeleteActivity.this, R.color.colorAccent));
            }
        };
        adapter.getDraggableModule().setSwipeEnabled(true);
        adapter.getDraggableModule().setOnItemSwipeListener(onItemSwipeListener);
        adapter.getDraggableModule().getItemTouchHelperCallback().setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void onRefresh() {
//        mHandler.postDelayed(mRefresh, 2000);
//        adapter.getLoadMoreModule().setEnableLoadMore(false);
//        pager.reset();
        mlist.clear();
        num=1;
        request();
    }


    private void request() {
        num++;
        for (int i = 0; i < 5; i++) {
            mlist.add(num + " 刷新" + i);
        }
        swipeRefreshLayout.setRefreshing(false);
        if (num<12) {
            adapter.getLoadMoreModule().loadMoreComplete();
        } else {
            adapter.getLoadMoreModule().loadMoreFail();
        }
        adapter.notifyDataSetChanged();
    }

    class Pager {
        int page = 0;

        void next() {
            page++;
        }

        void reset() {
            page = 0;
        }

        boolean isFirst() {
            return page == 0;
        }
    }

    class MAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements DraggableModule, LoadMoreModule {
        public MAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(R.id.tv_item_delete, s);
        }
    }
}
