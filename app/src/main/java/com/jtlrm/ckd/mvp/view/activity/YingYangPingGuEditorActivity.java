package com.jtlrm.ckd.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class YingYangPingGuEditorActivity extends TitleBarActivity {

    public static void goYinYang(Context context) {
        Intent intent = new Intent(context, YingYangPingGuEditorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "新增营养评估";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_ying_yang_ping_gu_detail);
    }

    @Override
    protected void initView() {
        titleBar.tvRight.setText("完成提交");
        titleBar.tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
