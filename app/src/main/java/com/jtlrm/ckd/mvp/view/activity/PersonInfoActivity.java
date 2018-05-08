package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.mvp.model.dao.UserHelper;

import butterknife.BindView;

public class PersonInfoActivity extends TitleBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected String getTitleText() {
        return "我的资料";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_person_info);
    }

    @Override
    protected void initView() {
        titleBar.tvRight.setText("保存");
    }

    @Override
    protected void obtainData() {

    }



    @Override
    protected void initEvent() {

    }

    public void selectHeader(View view) {
        startActivity(new Intent(context, HeaderChangeActivity.class));
    }

}
