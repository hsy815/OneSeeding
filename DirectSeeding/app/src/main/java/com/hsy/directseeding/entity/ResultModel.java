package com.hsy.directseeding.entity;

import java.io.Serializable;

/**
 * Created by .
 * 返回结果
 */
public class ResultModel<T> implements Serializable {
    private String errcode;
    private String errmsg;
    private T data;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
