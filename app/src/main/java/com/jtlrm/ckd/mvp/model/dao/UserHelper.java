package com.jtlrm.ckd.mvp.model.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jtlrm.ckd.entity.LoginResult;
import com.jtlrm.ckd.entity.UserEntity;

/**
 * Created by Administrator on 2018/3/27/027.
 */

public class UserHelper {
    private static UserHelper helper;
    private SharedPreferences sharedPreferences;
    private static final String USER_INFO = "user_info";
    private static final String USER = "user";
    private static final String LOGIN = "login"; // 包含token等值
    private String token;

    private UserHelper() {
    }

    private UserHelper(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
    }

    /**
     * 获取用户实体类
     *
     * @return
     */
    public static synchronized UserHelper getInstance(Context context) {
        if (helper == null) {
            helper = new UserHelper(context);
        }
        return helper;
    }


    public String getToken() {
        if (TextUtils.isEmpty(token)) {
            String login = sharedPreferences.getString(LOGIN, "");
            LoginResult result;
            if (TextUtils.isEmpty(login)) {
                token = "";
            } else {
                Gson gson = new Gson();
                result = gson.fromJson(login, LoginResult.class);
                token = result.getAccess_token();
            }
        }
        return token;
    }

    public void setLogin(LoginResult loginResult) {
        this.token = loginResult.getAccess_token();
        if (!TextUtils.isEmpty(token)) {
            sharedPreferences.edit().putString(LOGIN, new Gson().toJson(loginResult)).commit();
        } else {
            // 清除个人数据
            sharedPreferences.edit().clear().commit();
        }
    }

    public UserEntity getUserInfo() {
        String userStr = sharedPreferences.getString(USER, "");
        UserEntity userEntity = new UserEntity();
        if (TextUtils.isEmpty(userStr)) {
            return userEntity;
        }
        Gson gson = new Gson();
        try {
            userEntity = gson.fromJson(userStr, UserEntity.class);
        } catch (Exception e) {

        }
        return userEntity;
    }

    public void setUserInfo(UserEntity userInfo) {
        if (userInfo != null) {
            sharedPreferences.edit().putString(USER, new Gson().toJson(userInfo)).commit();
        } else {
            // 清除个人数据
            sharedPreferences.edit().clear().commit();
        }
    }

}
