package com.ys.yarc.mvp.model.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ys.yarc.entity.UserEntity;

/**
 * Created by Administrator on 2018/3/27/027.
 */

public class UserHelper {
    private static UserHelper helper;
    private SharedPreferences sharedPreferences;
    private static final String USER_INFO = "user_info";
    private static final String USER = "user";
    private static final String TOKEN = "token";
    private String token;

    private UserHelper() {
    }

    private UserHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
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
            token = sharedPreferences.getString(TOKEN, "");
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        if (!TextUtils.isEmpty(token)) {
            sharedPreferences.edit().putString(TOKEN, token).commit();
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
