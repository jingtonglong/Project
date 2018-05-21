package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

/**
 * 量表评估子界面
 */
public class LiangBiaoPingGuSubListActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "量表评估";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_liang_biao_ping_gu_sub_list);
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
