package com.ys.yarc.mvp.view.activity;

import android.os.Bundle;
import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.ys.yarc.R;
import com.ys.yarc.base.acitvity.BaseActivity;

import butterknife.BindView;

public class NoticeSettingActivity extends BaseActivity {
    @BindView(R.id.notice_setting_title)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_notice_setting);
    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("通知设置");
        titleBar.imgLeft.setVisibility(View.VISIBLE);
        titleBar.imgLeft.setOnClickListener(new View.OnClickListener() {
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
