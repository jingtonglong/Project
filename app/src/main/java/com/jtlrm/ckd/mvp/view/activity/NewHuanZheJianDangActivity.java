package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class NewHuanZheJianDangActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "新患者建档";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_new_huan_zhe_jian_dang);
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

    public void submit(View view) {
        startActivity(new Intent(context, NewHuanZheJianDangActivity2.class));
    }
}
