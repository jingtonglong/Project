package com.jtlrm.ckd.entity;

/**
 * Created by Administrator on 2018/3/22/022.
 */

public class ResultData<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
