package com.example.kotlintestdemo.mvp.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseActivity;

import java.lang.ref.WeakReference;

public class HandlerActivity extends BaseActivity {

    TextView textView;
    private myHandler handler = new myHandler(this);
    @Override
    public int layoutId() {
        return R.layout.activity_handler;
    }
    @Override
    public void initView() {
        textView = findViewById(R.id.tv_handler);
        begain();
    }

    private void begain() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    Message message = new Message();
                    message.what = 1;
                    message.arg1 = i;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(HandlerActivity.this, DeleteActivity.class));
                finish();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private static class myHandler extends Handler {
        WeakReference<HandlerActivity> weakReference;

        public myHandler(HandlerActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            HandlerActivity activity = weakReference.get();
            if (activity != null) {
                activity.textView.setText(String.valueOf(msg.arg1));
            }
        }
    }
}
