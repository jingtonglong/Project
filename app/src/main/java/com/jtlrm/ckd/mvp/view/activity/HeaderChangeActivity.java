package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.base.sdk.util.camera.CameraSelectUtil;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class HeaderChangeActivity extends TitleBarActivity {
    CameraSelectUtil cameraSelectUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected String getTitleText() {
        return "个人头像";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_header_change);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initView() {
        titleBar.imgRight.setImageResource(R.drawable.title_more);
        titleBar.imgRight.setVisibility(View.VISIBLE);
        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cameraSelectUtil == null) {
                    cameraSelectUtil = new CameraSelectUtil(context);
                }
                cameraSelectUtil.selectPicBoth();
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
