package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.base.sdk.util.AppUtil;
import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.util.CommonUtil;

import butterknife.BindView;

/**
 * app更新检查
 */
public class UpdateAppActivity extends BaseActivity {
    @BindView(R.id.update_title)
    TitleBar titleBar;
    @BindView(R.id.update_version)
    TextView versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_update_app);
    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("更新检查");
        titleBar.imgLeft.setVisibility(View.VISIBLE);
        titleBar.imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        versionName.setText("当前版本:" + AppUtil.getAppVersionName(context));
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
