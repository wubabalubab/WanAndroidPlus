package com.example.kotlintestdemo.mvp.view.activity;

import android.widget.TextView;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class KnifeActivity extends BaseActivity {

    @BindView(R.id.tvtest)
    TextView tvtext;

    @Override
    public int layoutId() {
        return R.layout.activity_knife;
    }

    @Override
    public void initView() {
        tvtext.setText("adsfasdfasdfasdfasd");
        initdata();
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
