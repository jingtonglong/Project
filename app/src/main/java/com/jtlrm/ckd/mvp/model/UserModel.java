package com.jtlrm.ckd.mvp.model;

import com.base.sdk.base.model.BaseModel;
import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.base.net.LifeCycleEvent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jtlrm.ckd.entity.submitEntity.RegisterData;
import com.jtlrm.ckd.net.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by Administrator on 2018/3/1/001.
 */

public class UserModel extends BaseModel {

    public UserModel() {

    }

    public void login(String username, String password, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("grant_type", "password");
        jsonObject.addProperty("client_id", "app");
        jsonObject.addProperty("scope", "ckdDoctor");
        jsonObject.addProperty("client_secret", "123123");
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().login(jsonObject), observer, lifecycleSubject);
    }

    public void getUserInfo(Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().getUserInfo(), observer, lifecycleSubject);
    }


    public void sendMessage(String phone, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile", phone);
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().sendMessgae(jsonObject), observer, lifecycleSubject);
    }

    public void getToken( Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("grant_type", "client_credentials");
        jsonObject.addProperty("client_id", "ios_123");
        jsonObject.addProperty("client_secret", "123123");
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().login(jsonObject), observer, lifecycleSubject);
    }

    public void register(RegisterData data, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().register(createBody(data)), observer, lifecycleSubject);
    }

}
