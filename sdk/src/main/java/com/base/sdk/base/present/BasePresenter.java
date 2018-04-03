package com.base.sdk.base.present;


import android.text.TextUtils;

import com.base.sdk.base.api.IBaseView;

/**
 * date：     2017/9/13
 * version    1.0
 * description
 * modify by
 */
public abstract class BasePresenter<T extends IBaseView> {

    protected T mIView;

    public BasePresenter(T t) {
        mIView = t;
    }


    protected boolean notEmpty(String content) {
        if (TextUtils.isEmpty(content)) {
            return false;
        } else {
            return true;
        }
    }
}
