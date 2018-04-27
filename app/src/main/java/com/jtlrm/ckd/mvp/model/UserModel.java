package com.jtlrm.ckd.mvp.model;


import com.base.sdk.base.entity.RequestResult;
import com.base.sdk.base.model.BaseModel;
import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.base.net.LifeCycleEvent;
import com.google.gson.JsonObject;
import com.jtlrm.ckd.entity.LoginResult;
import com.jtlrm.ckd.entity.ResultData;
import com.jtlrm.ckd.entity.UserEntity;
import com.jtlrm.ckd.net.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by Administrator on 2018/3/1/001.
 */

public class UserModel extends BaseModel {

    public UserModel() {

    }

    public void login(String username, String password, CommonObserver observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
       JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("account", username);
        jsonObject.addProperty("password", password);
        Observable<RequestResult<LoginResult>> observable = RetrofitUtil.getService().login(jsonObject);
        RetrofitUtil.composeToSubscribe(observable, observer, lifecycleSubject);
    }


    public void updateUserInfo(CommonObserver observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        Observable<RequestResult<ResultData<UserEntity>>> observable = RetrofitUtil.getService().getUserInfo();
        RetrofitUtil.composeToSubscribe(observable, observer, lifecycleSubject);
    }

    public void register(CommonObserver observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", "11223344551");
        jsonObject.addProperty("password", "123456");
        jsonObject.addProperty("name", "1122");
        Observable<RequestResult> observable = RetrofitUtil.getService().register(jsonObject);
        RetrofitUtil.composeToSubscribe(observable, observer, lifecycleSubject);
    }
}
