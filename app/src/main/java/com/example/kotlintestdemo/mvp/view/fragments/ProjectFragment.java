package com.example.kotlintestdemo.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.FgProjectExpandableAdapter;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.mvp.contract.ProjectFgContract;
import com.example.kotlintestdemo.mvp.presenter.ProjectFgPresenter;
import com.example.kotlintestdemo.mvp.view.activity.ResoursActivity;
import com.example.kotlintestdemo.util.MyConstant;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ProjectFragment extends BaseMvpFragment<ProjectFgPresenter> implements ProjectFgContract.View {
    private static final String TAG = "ProjectFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.espandablelist)
    ExpandableListView espandablelist;
    @BindView(R.id.rv_fgprojectlist)
    RecyclerView recyclerView;
    FgProjectExpandableAdapter adapter;
    mAdapter mAdapter;
    List<data.DatasBean> beanList = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private List<TixiBean> list = new ArrayList<>();
    private int page = 0;
    private int cid = 0;
    private boolean first=true;
    private boolean isAdd=false;

    public ProjectFragment() {
    }

    public static ProjectFragment newInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int layoutid() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView(View View) {
        mPresent = new ProjectFgPresenter();
        mPresent.attachView(ProjectFragment.this);
        mPresent.showTixiList();

        adapter = new FgProjectExpandableAdapter(getActivity(), list);
        espandablelist.setAdapter(adapter);
        espandablelist.setGroupIndicator(null);
        espandablelist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, android.view.View v, int groupPosition, int childPosition, long id) {

                isAdd=false;
                mPresent.showData(0, list.get(groupPosition).getChildren().get(childPosition).getId());
                cid=list.get(groupPosition).getChildren().get(childPosition).getId();
                page=0;
                Log.e(TAG, "onChildClick: cid"+cid);
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new mAdapter(beanList);
        mAdapter.getLoadMoreModule();
        recyclerView.setAdapter(mAdapter);

        mAdapter.getLoadMoreModule().setAutoLoadMore(false);
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mAdapter.getLoadMoreModule().isLoading();
                page+=page;
                isAdd=true;
                mPresent.showData(page,cid);
                Log.e(TAG, "onLoadMore: page cid"+page+" "+cid);
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull android.view.View view, int position) {
                Intent intent=new Intent(getContext(),ResoursActivity.class);
                intent.putExtra(MyConstant.CONTENT_URL, beanList.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setTixiList(BaseObjectBean<List<TixiBean>> bean) {
        if (bean.getErrorCode()==0) {
            list.addAll(bean.getData());
            if (first) {
                mPresent.showData(0, list.get(0).getChildren().get(0).getId());
                first=false;
            }else {
                mPresent.showData(page, list.get(0).getChildren().get(cid).getId());
                first=false;
            }
            isAdd=false;
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setData(BaseObjectBean<data> bean) {
        if (bean.getErrorCode()==0) {
            if (!isAdd) {
                beanList.clear();
            }
            beanList.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
            if (bean.getData().getDatas().size() > 0) {
                mAdapter.getLoadMoreModule().loadMoreComplete();
            }else {
                mAdapter.getLoadMoreModule().loadMoreEnd();
            }
        }
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {

    }

    class mAdapter extends BaseQuickAdapter<data.DatasBean, BaseViewHolder> implements LoadMoreModule{

        public mAdapter(@Nullable List<data.DatasBean> data) {
            super(R.layout.item_rv_fgproject, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, data.DatasBean data) {

            baseViewHolder.setText(R.id.tv_item_rv_fgproject_title, data.getTitle());
            baseViewHolder.setText(R.id.tv_item_rv_fgproject_classify, data.getChapterName());
            baseViewHolder.setText(R.id.tv_item_rv_fgproject_classify2, data.getSuperChapterName());
            if (data.getAuthor()!=null&& !TextUtils.isEmpty(data.getAuthor())) {
                baseViewHolder.setText(R.id.tv_item_rv_fgproject_author, data.getAuthor());
            } else {
                baseViewHolder.setText(R.id.tv_item_rv_fgproject_author, data.getShareUser());
            }
        }
    }
}
