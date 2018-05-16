package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

public class ShengHuaDataEditorActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "新增数据";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_sheng_hua_data_editor);
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
