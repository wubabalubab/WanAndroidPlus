package com.example.kotlintestdemo.mvp.view.activity;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseActivity;
import com.example.kotlintestdemo.util.BitmapUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class XBannerActivity extends BaseActivity implements OnBannerListener {

    private static final int permissionrequestcode = 34;
    private static final String TAG = "XBannerActivity";
    private ImageView imBack;
    private Banner banner;
    private ArrayList<String> listData;
    private ArrayList<String> listTitle;
    private ArrayList<String> listColor;

    private Bitmap bitmap;
    private int color;
    private int position;
    private List<Bitmap> bitmapList;

    MyThread myThread=new MyThread();
//    Thread thread =new Thread(myThread);

    private MyHandler myHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestPermisson();
    }

    @Override
    public int layoutId() {
        return R.layout.activity_x_banner;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void initView() {

        banner = findViewById(R.id.banner);
        imBack = findViewById(R.id.im_back);
        listData = new ArrayList<>();
        listTitle = new ArrayList<>();
        bitmapList = new ArrayList<>();
        listColor=new ArrayList<>();

        listData.add("https://images.cnblogs.com/cnblogs_com/baimiyishu/1821847/o_200805141726berserk-004.jpg");
        listData.add("https://images.cnblogs.com/cnblogs_com/baimiyishu/1821847/o_200805141716749693.jpg");
        listData.add("https://images.cnblogs.com/cnblogs_com/baimiyishu/1821847/o_200805141726berserk-004.jpg");
        listData.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        listTitle.add("好好学习");
        listTitle.add("天天向上");
        listTitle.add("热爱劳动");
        listTitle.add("不搞对象");

        listColor.add("#000000");
        listColor.add("#6200EE");
        listColor.add("#3700B3");
        listColor.add("#03DAC5");


        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new Myload());
        banner.setImages(listData);
        banner.setBannerAnimation(Transformer.Default);
        banner.setBannerTitles(listTitle);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "onPageScrolled: "+position );
            }
            @Override
            public void onPageSelected(int positin) {


//                imBack.setBackgroundColor(Color.parseColor(listColor.get(positin)));
//                     thread.start();
//                    new MyThread().start();
                if (position!=positin) {
                    position = positin;
                    int color=Color.parseColor(listColor.get(positin));
                    int colornext;
                    Log.e(TAG, "onPageSelected: "+positin);
                    if (positin==listColor.size()-1) {
                        colornext=Color.parseColor(listColor.get(0));
                    }else {
                        colornext=Color.parseColor(listColor.get(positin+1));
                    }
                    ValueAnimator valueAnimator= ObjectAnimator.ofInt(imBack,"backgroundColor",color,colornext);
                    valueAnimator.setDuration(500);
                    valueAnimator.setEvaluator(new ArgbEvaluator());
                    valueAnimator.setRepeatCount(0);
                    valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
                    valueAnimator.start();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageScrollStateChanged: "+state );
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        myThread.onPauseThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myThread.onResumeThread();
    }


    private class MyThread extends Thread{
        private boolean pause =false;
        private final Object lock=new Object();

        void onPauseThread(){
            pause=true;
        }
        void onResumeThread(){
            pause=false;
            synchronized (lock){
                lock.notifyAll();
            }
        }
        void onPause(){
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    while (pause) {
                        onPause();
                    }
                    Message message = new Message();
                    message.what = 1;
                    BitmapUtil util = new BitmapUtil();
                    try {
                    URL url = null;
                        url = new URL(listData.get(position));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                    InputStream inputStream = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    message.arg1 = util.resultBgcolor(bitmap);
                    myHandler.sendMessage(message);
                }
            } catch (NullPointerException  e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 'this' is call back
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(permissionrequestcode)
    private void RequestPermisson() {
        String[] permissons = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, permissons)) {
            Toast.makeText(this, "permissons success!", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.app_name), permissionrequestcode, permissons);
        }
    }

    private static class MyHandler extends Handler {
        WeakReference<XBannerActivity> weakReference;

        public MyHandler(XBannerActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            XBannerActivity activity = weakReference.get();
            Bitmap bitmap = null;
            if (msg.what == 1) {

                if (activity != null) {

                    Log.e(TAG, "handleMessage:  argu  "+msg.arg1 );
                        activity.imBack.setBackgroundColor(msg.arg1);

                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
        myThread.interrupt();
    }

    private class Myload extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
