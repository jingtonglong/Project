package com.base.sdk.base.entity;

/**
 * 在外层请求结果
 * Created by Administrator on 2018/3/22/022.
 */

public class RequestResult<T> {
    private int code;
    private String msg;
    private boolean isException;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isException() {
        return isException;
    }

    public void setException(boolean exception) {
        isException = exception;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
