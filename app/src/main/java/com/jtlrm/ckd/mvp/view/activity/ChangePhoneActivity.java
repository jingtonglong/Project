package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import butterknife.BindView;

/**
 * 修改电话号码
 */
public class ChangePhoneActivity extends BaseActivity {
    @BindView(R.id.change_phone_title)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_change_phone);
    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("修改手机号");
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
