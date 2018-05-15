package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class CompleteInfoActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "完善资料";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_complete_info);
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

    public void submit(View v) {
        startActivity(new Intent(context, RegisterSuccessfulActivity.class));
    }
}
