package com.example.kotlintestdemo.base;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsListener;

public class BaseApplication extends Application {
    public static BaseApplication Myapp;
    private static final String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Myapp=this;
        QbSdk.PreInitCallback cb=new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.e(TAG, "内核加载结果: "+b );
            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {

            }

            @Override
            public void onInstallFinish(int i) {
                Log.e(TAG, "onInstallFinish: 安装下载完成" );
            }

            @Override
            public void onDownloadProgress(int i) {

            }
        });
        QbSdk.initX5Environment(getApplicationContext(), cb);
        boolean needdownload= TbsDownloader.needDownload(this, TbsDownloader.DOWNLOAD_OVERSEA_TBS);
        if (needdownload) {
            TbsDownloader.startDownload(this);
        }

    }
    public static BaseApplication getMyapp(){
        return Myapp;
    }
}
