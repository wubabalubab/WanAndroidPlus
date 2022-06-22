package com.example.kotlintestdemo.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseActivity;
import com.example.kotlintestdemo.util.MyConstant;

import butterknife.BindView;
import butterknife.OnClick;

public class ResoursActivity extends BaseActivity {

    @BindView(R.id.im_actresours)
    ImageView imBack;
    @BindView(R.id.im_actresours_collection)
    ImageView imCollection;

    private String url = "";


    // TODO: 22-6-23 webview 待优化
    @OnClick({R.id.im_actresours_collection, R.id.im_actresours})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.im_actresours:
                finish();
                break;
            case R.id.im_actresours_collection:
                setCollection();
                break;
            default:
                break;
        }
    }

    private void setCollection() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public int layoutId() {
        return R.layout.activity_resours;

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(MyConstant.CONTENT_URL);
        }

    }
}
