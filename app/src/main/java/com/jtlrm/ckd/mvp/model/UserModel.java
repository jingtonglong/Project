package com.jtlrm.ckd.mvp.model;

import com.base.sdk.base.model.BaseModel;
import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.base.net.LifeCycleEvent;
import com.google.gson.JsonObject;
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

    public void register(CommonObserver observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", "11223344551");
        jsonObject.addProperty("password", "123456");
        jsonObject.addProperty("name", "1122");
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().register(jsonObject), observer, lifecycleSubject);
    }

    public void sendMessage(String phone, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile", phone);
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().sendMessgae(jsonObject), observer, lifecycleSubject);
    }
}
