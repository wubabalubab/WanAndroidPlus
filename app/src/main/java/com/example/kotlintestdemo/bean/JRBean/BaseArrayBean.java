package com.example.kotlintestdemo.bean.JRBean;

import java.util.List;

public class BaseArrayBean<T> {
        private String  errorMessage;
        private int errorcode;
        private List<T> result;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }


    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseArrayBean{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorcode=" + errorcode +
                ", result=" + result +
                '}';
    }
}
