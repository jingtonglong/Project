package com.ys.yarc.entity;

/**
 * Created by Administrator on 2018/3/27/027.
 */

public class LoginResult {
    private String data;
    private UserEntity userInfo;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public UserEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserEntity userInfo) {
        this.userInfo = userInfo;
    }

}
