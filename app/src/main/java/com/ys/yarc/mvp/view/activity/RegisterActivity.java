package com.ys.yarc.mvp.view.activity;

import android.os.Bundle;

import com.base.sdk.base.net.CommonObserver;
import com.ys.yarc.R;
import com.ys.yarc.base.acitvity.BaseActivity;
import com.ys.yarc.mvp.model.UserModel;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity {

    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        userModel = new UserModel();
        userModel.register(new CommonObserver<Object>() {
            @Override
            public void onError(int errType, String errMessage) {
                showToast("注册失败," + errMessage);
            }

            @Override
            public void onResult(Object data) {
                showToast("注册成功");
                finish();
            }
        }, getLifeSubject());
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
