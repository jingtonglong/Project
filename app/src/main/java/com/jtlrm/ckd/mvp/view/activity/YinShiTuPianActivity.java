package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.view.View;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class YinShiTuPianActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "图片预览";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_yin_shi_tu_pian);
    }

    @Override
    protected void initView() {
        titleBar.tvRight.setText("关闭");
        titleBar.tvRight.setVisibility(View.VISIBLE);
        titleBar.btLeft.setVisibility(View.GONE);
        titleBar.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
