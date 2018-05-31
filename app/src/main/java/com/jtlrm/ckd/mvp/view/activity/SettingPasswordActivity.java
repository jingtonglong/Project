package com.jtlrm.ckd.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingPasswordActivity extends RebuilPasswordActivity {

    String phone;

    public static void goSetting(Context context, String phone) {
        Intent intent = new Intent(context, SettingPasswordActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        phone = getIntent().getStringExtra("phone");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "设置密码";
    }

    @Override
    public void submit(View view) {
        if (passwordIsOk()) {
            CompleteInfoActivity.goActivity(context, passwordStr, phone);
        }
    }
}
