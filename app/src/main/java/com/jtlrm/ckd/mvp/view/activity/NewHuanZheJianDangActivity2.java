package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class NewHuanZheJianDangActivity2 extends TitleBarActivity {

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
        setContentView(R.layout.activity_new_huan_zhe_jian_dang2);
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
}
