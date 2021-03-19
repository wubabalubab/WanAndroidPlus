package com.example.kotlintestdemo.mvp.view.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.AdapterHelperDemo;
import com.example.kotlintestdemo.adapter.SharedAdapter;
import com.example.kotlintestdemo.bean.JRBean.appInfo;
import com.example.kotlintestdemo.bean.JRBean.articleBean;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterHelperDemo extends AppCompatActivity {

    ArrayList<articleBean> mList;
    RecyclerView recyclerView;
    AdapterHelperDemo adapterhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter_helper_demo);
        mList=new ArrayList<>();
        articleBean articleBean;
        for (int i = 0; i < 5; i++) {
            articleBean=new articleBean(i, i +"name");
            mList.add(articleBean);
        }
        Log.e("xxx", "onCreate: "+mList.toString() );

        recyclerView=findViewById(R.id.rv_helper);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterhelp=new AdapterHelperDemo(R.layout.item_adapterhelperdemo,mList);
        recyclerView.setAdapter(adapterhelp);
        Toast.makeText(this, mList.toString(), Toast.LENGTH_SHORT).show();

        adapterhelp.addChildClickViewIds(R.id.tv_item_adapterhelper,R.id.tv_item_adapterhelpername,R.id.im_item_adapterdemo);
        adapterhelp.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId() ) {
                    case  R.id.tv_item_adapterhelper:
                        Intent send = new Intent(Intent.ACTION_SEND);  //隐式intent的action
                        send.setType("text/plain");
                        send.putExtra(Intent.EXTRA_TEXT, "测试，这里发送推广地址");
                        startActivity(Intent.createChooser(send, "share title"));
                        break;
                    case R.id.tv_item_adapterhelpername:
                        initSharedWindow();
                        break;

                }
            }
        });
    }

    private void initSharedWindow() {
        PopupWindow shareWindow=null;
        View  view=null;
        RecyclerView recyclerView=null;

            view= LayoutInflater.from(BaseAdapterHelperDemo.this).inflate(R.layout.item_rv_shared, null);
            recyclerView=view.findViewById(R.id.rv_sharedwindow);
            List<appInfo> appInfoList=getShareAppList();
             SharedAdapter adapter=new SharedAdapter(appInfoList);
            recyclerView.setLayoutManager(new GridLayoutManager(this,5,RecyclerView.VERTICAL,false));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    appInfo appInfo = (appInfo) adapter.getItem(position);
                    shareIntent.setComponent(new ComponentName(appInfo.getAppPKname(), appInfo.getApplaunchname()));
                    shareIntent.setType("text/plain");
                    //这里就是组织内容了，
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "测试，这里发送推广地址");  //发送的内容
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(shareIntent);
                }
            });
            shareWindow=new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);


        shareWindow.setFocusable(true);
        shareWindow.setOutsideTouchable(true);
        shareWindow.setBackgroundDrawable(new BitmapDrawable());
//        shareWindow.showAsDropDown(parent);
        shareWindow.showAtLocation(findViewById(R.id.rlss), Gravity.BOTTOM, 0, 0);

    }

    private ArrayList<appInfo> getShareAppList() {
        ArrayList<appInfo> shareAppInfos = new ArrayList<appInfo>();
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfos = getShareApps(this);
        if (null == resolveInfos) {
            return null;
        } else {
            for (ResolveInfo resolveInfo : resolveInfos) {
                Log.e("zxy","packageName::" + resolveInfo.activityInfo.packageName);
                Log.e("zxy","AppLauncherClassName::" + resolveInfo.activityInfo.name);
                Log.e("zxy","appName::" + resolveInfo.loadLabel(packageManager).toString());

                appInfo appInfo = new appInfo();
                if (resolveInfo.activityInfo.packageName.equals("com.tencent.tim") || resolveInfo.activityInfo.packageName.equals("com.tencent.mobileqq") ||
                        resolveInfo.activityInfo.packageName.equals("com.tencent.mm") || resolveInfo.activityInfo.packageName.equals("com.qzone") ||
                        resolveInfo.activityInfo.packageName.equals("com.sina.weibo") || resolveInfo.activityInfo.packageName.equals("com.android.mms")) {

                    appInfo.setAppPKname(resolveInfo.activityInfo.packageName);
                    appInfo.setApplaunchname(resolveInfo.activityInfo.name);
                    String appName = resolveInfo.loadLabel(packageManager).toString();
                    appInfo.setAppname(appName);
                    appInfo.setDrawable(resolveInfo.loadIcon(packageManager));
                    shareAppInfos.add(appInfo);
                }
            }
        }
        return shareAppInfos;
    }

    @SuppressLint("WrongConstant")
    public List<ResolveInfo> getShareApps(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");  //发送什么类型的文件，这里发送的为text，比如说相册发送的是图片类型的文件
        PackageManager pManager = context.getPackageManager();
        List<ResolveInfo> mApps = pManager.queryIntentActivities(intent,
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return mApps;
    }
}
