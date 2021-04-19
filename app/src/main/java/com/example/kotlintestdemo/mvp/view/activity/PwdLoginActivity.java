package com.example.kotlintestdemo.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.base.BaseMvpActivity;
import com.example.kotlintestdemo.bean.JRBean.BaseObjectBean;
import com.example.kotlintestdemo.bean.JRBean.UserBean;
import com.example.kotlintestdemo.mvp.contract.PwdLoginActContract;
import com.example.kotlintestdemo.mvp.presenter.PwdLoginActPresenter;
import com.example.kotlintestdemo.util.MyConstant;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
@SuppressLint("NonConstantResourceId")
public class PwdLoginActivity extends BaseMvpActivity<PwdLoginActPresenter> implements PwdLoginActContract.View {


    @BindView(R.id.ed_actpwdlogin_username)
    EditText edUsername;
    @BindView(R.id.ed_actpwdlogin_password)
    EditText edPasswd;
    @BindView(R.id.ed_actpwdlogin_repassword)
    EditText edRePasswd;
    @BindView(R.id.bt_actpwdlogin_login)
    Button btLogin;
    @BindView(R.id.bt_actpwdlogin_register)
    Button btRegiter;

    private static final String TAG = "PwdLoginActivity";

    @Override
    public int layoutId() {
        return R.layout.activity_pwd_login;
    }

    @Override
    public void initView() {
        mPresenter = new PwdLoginActPresenter();
        mPresenter.attachView(PwdLoginActivity.this);
    }


    @OnClick({R.id.bt_actpwdlogin_login, R.id.bt_actpwdlogin_register})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.bt_actpwdlogin_login:
                if (checktest()) {
                    mPresenter.showLogin(String.valueOf(edUsername.getText()), String.valueOf(edPasswd.getText()));
                }
                break;

            case R.id.bt_actpwdlogin_register:
                if (edRePasswd.getVisibility() == View.VISIBLE) {
                    if (checktest()) {
                        if (!TextUtils.isEmpty(edRePasswd.getText()) &&
                                String.valueOf(edPasswd.getText()).equals(String.valueOf(edRePasswd.getText()))) {
                            mPresenter.showRegister(String.valueOf(edUsername.getText()),
                                    String.valueOf(edPasswd.getText()),String.valueOf(edRePasswd.getText()));
                        } else {
                            Toast.makeText(this, "密码不一致！", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                  edRePasswd.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
    public boolean checktest(){
        if (!TextUtils.isEmpty(edUsername.getText())) {
            if (!TextUtils.isEmpty(edPasswd.getText()) && edPasswd.getText().length() >= 6) {
                return true;
            } else {
                Toast.makeText(this, "密码不合法！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "用户名不合法！", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setLogin(BaseObjectBean<UserBean> bean) {
        if (bean.getErrorCode()==0) {
            saveBeanToSp(bean.getData());
            setResult(200);
            finish();
        }else {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }
    private void saveBeanToSp(UserBean bean){
        SharedPreferences sp=this.getSharedPreferences(MyConstant.SP_CACHE, MODE_PRIVATE);
        sp.edit().putString("UserBean",new Gson().toJson(bean)).apply();
    }

    @Override
    public void setRegister(BaseObjectBean<UserBean> bean) {
        if (bean.getErrorCode()==0) {
            saveBeanToSp(bean.getData());
            setResult(200);
            finish();
        }else {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void setLogout(BaseObjectBean<String> bean) {

    }

    @Override
    public void onError(String message) {

    }
}