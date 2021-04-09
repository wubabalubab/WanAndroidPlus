package com.example.kotlintestdemo.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseActivity;
import com.example.kotlintestdemo.util.MyConstant;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;

public class ResoursActivity extends BaseActivity {

    @BindView(R.id.web_actresours)
    WebView webView;
    @BindView(R.id.im_actresours)
    ImageView imBack;
    @BindView(R.id.im_actresours_collection)
    ImageView imCollection;

    private String url = "";
    private WebViewClient client = new WebViewClient() {
        public boolean shouldorrerUrl(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }

        public void onResponseRequest(WebView webView, com.tencent.smtt.export.external.interfaces.HttpAuthHandler httpAuthHandler,
                                      String host, String realm) {
            boolean flag = httpAuthHandler.useHttpAuthUsernamePassword();
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
        }

        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
        }

        @Override
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }
    };

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
        if (webView != null) {
            webView.destroy();
        }
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
        if (url != null && !TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }
        //支持javascript  
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(client);
    }
}
