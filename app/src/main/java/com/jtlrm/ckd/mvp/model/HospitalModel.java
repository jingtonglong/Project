package com.jtlrm.ckd.mvp.model;

import com.base.sdk.base.model.BaseModel;
import com.base.sdk.base.net.LifeCycleEvent;
import com.google.gson.JsonObject;
import com.jtlrm.ckd.net.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

/**
 * 机构相关
 */
public class HospitalModel extends BaseModel {

    public void queryHospital(String ley, Observer observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Keyword", ley);
        RetrofitUtil.composeToSubscribe(RetrofitUtil.getService().queryHospital(jsonObject), observer, lifecycleSubject);
    }

}
