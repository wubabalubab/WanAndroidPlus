package com.example.kotlintestdemo.net;


import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.bean.loginbean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
*   author K.K
*   created on 20/12/2020
*   @Describe APIService
*/

public interface APIService {

    public static final String baseUrl = "https://www.wanandroid.com";

    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseObjectBean<loginbean>> login(
            @Field("username") String username, @Field("password") String password);


    @GET("article/list/{page}/json")
    Observable<BaseObjectBean<data>> homeData(@Path("page")int page);
}