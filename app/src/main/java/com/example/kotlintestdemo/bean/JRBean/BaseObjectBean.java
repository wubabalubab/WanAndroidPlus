package com.example.kotlintestdemo.bean.JRBean;


public class BaseObjectBean<T> {
    private String errorMsg;
    private int errorCode;
    private T data;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "BaseObjectBean{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }
}

