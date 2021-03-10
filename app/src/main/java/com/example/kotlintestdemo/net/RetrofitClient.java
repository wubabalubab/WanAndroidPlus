package com.example.kotlintestdemo.net;

import android.util.Log;

import com.example.kotlintestdemo.base.BaseApplication;
import com.example.kotlintestdemo.util.MyConstant;
import com.example.kotlintestdemo.util.SystemUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static volatile RetrofitClient instance;
    private APIService apiService;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        Cache cache=new Cache(new File(BaseApplication.getMyapp().getCacheDir(),"logCache"),1024*1024*10);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {

                if (s.length() == 0)
                    return;
                int segmentSize = 3 * 1024;
                long length = s.length();
                if (length <= segmentSize) {// 长度小于等于限制直接打印
                    Log.i(MyConstant.HTTPLOG, s);
                } else {
                    while (s.length() > segmentSize) {// 循环分段打印日志
                        String logContent = s.substring(0, segmentSize);
                        s = s.replace(logContent, "");
                        Log.i(MyConstant.HTTPLOG, logContent);
                    }
                    Log.i(MyConstant.HTTPLOG, s);// 打印剩余日志
                }
            }
        });
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        MyCachIntercepter myCachIntercepter = new MyCachIntercepter();

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(myCachIntercepter)
                    .addInterceptor(httpLoggingInterceptor)
//                    .cookieJar()
                    .retryOnConnectionFailure(true)
                    .build();
        }
        return okHttpClient;
    }

    public APIService getAPIService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIService.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        if (apiService == null) {
            apiService = retrofit.create(APIService.class);
        }
        return apiService;
    }

    /**
     * 日志log拦截器
     */
    private class MyCachIntercepter implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();
            if (!SystemUtil.isNetConnected()) {
//                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (SystemUtil.isNetConnected()) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
//                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }
}
