package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import butterknife.BindView;

/**
 * 我的二维码
 */
public class MyQRCodeActivity extends BaseActivity {
    @BindView(R.id.qr_code_title)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_my_qrcode);
    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("我的二维码");
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
