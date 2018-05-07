package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;

public class SettingPasswordActivity extends RebuilPasswordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "设置密码";
    }
}
