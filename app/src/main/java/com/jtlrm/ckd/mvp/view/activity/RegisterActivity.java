package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.base.sdk.base.net.CommonObserver;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.mvp.model.UserModel;

import butterknife.BindView;

/**
 * 注册界面
 */
public class RegisterActivity extends TitleBarActivity {


    @BindView(R.id.register_password_check)
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "注册账号";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    public void next(View view) {
        if (checkBox.isChecked()) {
            startActivity(new Intent(context, SettingPasswordActivity.class));
        }  else {
            showToast("请确认阅读服务条款");
        }
    }

    public void service(View view) {
        startActivity(new Intent(context, ServiceRuleActivity.class));
    }
}
