package com.example.kotlintestdemo.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseMvpActivity;
import com.example.kotlintestdemo.bean.BaseObjectBean;
import com.example.kotlintestdemo.bean.loginbean;
import com.example.kotlintestdemo.mvp.contract.SwipeContract;
import com.example.kotlintestdemo.mvp.presenter.SwipePresenter;

public class Swipe2Activity extends BaseMvpActivity<SwipePresenter> implements SwipeContract.View {


    EditText etUserName;
    EditText etPassword;
    Button btSubmit;

    @Override
    public int layoutId() {
        return R.layout.activity_swipe2;
    }

    @Override
    public void initView() {

        etUserName=findViewById(R.id.editText);
        etPassword=findViewById(R.id.editText2);
        btSubmit=findViewById(R.id.button);
        mPresenter=new SwipePresenter();
        mPresenter.attachView(this);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etUserName.getText().toString().trim().isEmpty() && !etPassword.getText().toString().trim().isEmpty()) {

                    mPresenter.login(etUserName.getText().toString().trim(), etPassword.getText().toString().trim());
                } else {
                    Toast.makeText(Swipe2Activity.this, "input!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "login error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(BaseObjectBean<loginbean> bean) {
        Toast.makeText(this, "login success!", Toast.LENGTH_SHORT).show();
    }
}
