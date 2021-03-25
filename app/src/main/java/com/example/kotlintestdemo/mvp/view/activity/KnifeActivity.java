package com.example.kotlintestdemo.mvp.view.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseActivity;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.util.MyConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class KnifeActivity extends BaseActivity {

    @BindView(R.id.tvtest)
    TextView tvtext;
    private List<data.DatasBean> list;
    private static final String TAG = "KnifeActivity";
    @Override
    public int layoutId() {
        return R.layout.activity_knife;
    }

    @Override
    public void initView() {
        tvtext.setText("adsfasdfasdfasdfasd");
        initdata();
        Intent intent=getIntent();
        list=intent.getParcelableArrayListExtra(MyConstant.SP_CACHE);
        Log.e(TAG, "initView: "+list.toString() );
    }

    public void initdata() {

        List<String> list=new ArrayList<>();
        list.add("asdsfa");
        list.add("asdsfa");
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("1", list);
        System.out.println(map.get("1"));
    }
}
