package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import butterknife.BindView;

/**
 * 账号安全
 */
public class ZhangHaoActivity extends BaseActivity {
    @BindView(R.id.zhanghao_title)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_zhang_hao);

    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("账号安全");
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

    public void changePassword(View view) {
        startActivity(new Intent(context, ChangePasswordActivity.class));
    }

    public void changePhone(View view) {
        startActivity(new Intent(context, ChangePhoneActivity.class));
    }

}
