package com.example.kotlintestdemo.net;


import com.example.kotlintestdemo.bean.JRBean.BannerBean;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;
import com.example.kotlintestdemo.bean.JRBean.data;
import com.example.kotlintestdemo.bean.JRBean.loginbean;

import java.util.List;

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

    /**
     * 首页文章列表
     * @param page 页码 0开始
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseObjectBean<data>> homeData(@Path("page")int page);
    /*
       获取banner
     */
    @GET("banner/json")
    Observable<BaseObjectBean<List<BannerBean>>> BannerData();
    /*
    获取首页置顶文章
     */
    @GET("article/top/json")
    Observable<BaseObjectBean<List<data.DatasBean>>> getTopArticle();
    /*
    体系三级列表
   */
    @GET("tree/json")
    Observable<BaseObjectBean<List<TixiBean>>> getTixiList();
}
