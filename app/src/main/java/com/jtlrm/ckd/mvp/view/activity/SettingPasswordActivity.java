package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingPasswordActivity extends RebuilPasswordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "设置密码";
    }

    @Override
    public void submit(View view) {
        startActivity(new Intent(context, CompleteInfoActivity.class));
    }
}
