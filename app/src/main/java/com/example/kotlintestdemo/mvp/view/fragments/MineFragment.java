package com.example.kotlintestdemo.mvp.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseFragment;
import com.example.kotlintestdemo.base.BaseMvpFragment;
import com.example.kotlintestdemo.bean.JRBean.UserBean;
import com.example.kotlintestdemo.mvp.presenter.PwdLoginActPresenter;
import com.example.kotlintestdemo.mvp.view.activity.PwdLoginActivity;
import com.example.kotlintestdemo.mvp.view.activity.ViewPager2Activity;
import com.example.kotlintestdemo.util.MyConstant;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import butterknife.BindView;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class MineFragment extends BaseFragment {


    @BindView(R.id.tv_fgmine_title)
    TextView tvTitle;
    @BindView(R.id.tv_fgmine_logout)
    TextView tvLogout;
    @BindView(R.id.tv_fgmine_tool)
    TextView tvMoreTool;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private UserBean userBean;
    private SharedPreferences sp;
    public MineFragment() {
    }
    @OnClick({R.id.cd_fgmine_login,R.id.tv_fgmine_collection})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.cd_fgmine_login:
                if ("登录或注册".equals(String.valueOf(tvTitle.getText()))) {
                    startActivityForResult(new Intent(getContext(), PwdLoginActivity.class),100);
                }
                break;
            case R.id.tv_fgmine_tool:
                break;
            case R.id.tv_fgmine_collection:
                startActivity(new Intent(getContext(), ViewPager2Activity.class));
                break;
            case R.id.tv_fgmine_logout:
                PwdLoginActivity activity=new PwdLoginActivity();
                PwdLoginActPresenter presenter=new PwdLoginActPresenter();
                presenter.attachView(activity);
                presenter.showLogout();

                sp.edit().putString("UserBean", "").apply();
                tvTitle.setText("登录或注册");
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==200) {
            try {
                userBean=new Gson().fromJson(sp.getString("UserBean", ""), UserBean.class);
                tvTitle.setText(userBean.getNickname());
            } catch (JsonParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int layoutid() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!"登录或注册".equals(String.valueOf(tvTitle.getText()))) {
            tvLogout.setVisibility(android.view.View.VISIBLE);
        }
    }

    @Override
    protected void initView(View View) {
        sp=getContext().getSharedPreferences(MyConstant.SP_CACHE, Context.MODE_PRIVATE);
        if (!TextUtils.isEmpty(sp.getString("UserBaen", ""))) {
            userBean=new Gson().fromJson(sp.getString("UserBean", ""), UserBean.class);
            tvTitle.setText(userBean.getNickname());
        }

    }
}
