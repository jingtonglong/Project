package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class AboutUsActivity extends TitleBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "关于我们与帮助";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_about_us);
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
