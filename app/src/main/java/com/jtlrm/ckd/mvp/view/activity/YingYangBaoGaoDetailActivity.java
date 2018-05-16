package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.view.View;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

public class YingYangBaoGaoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_ying_yang_bao_gao_detail);
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

    public void goBack(View view) {
        finish();
    }
}
