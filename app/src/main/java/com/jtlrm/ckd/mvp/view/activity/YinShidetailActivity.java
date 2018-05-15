package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

import butterknife.BindView;

public class YinShidetailActivity extends TitleBarActivity {

    @BindView(R.id.picture_browse)
    TextView pictureBrowse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "饮食详情";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_yin_shidetail);
    }

    @Override
    protected void initView() {
        titleBar.tvRight.setText("修改");
        titleBar.tvRight.setVisibility(View.VISIBLE);
        titleBar.tvMiddle.setTextColor(getResourceColor(R.color.white));
        titleBar.tvRight.setTextColor(getResourceColor(R.color.white));
        titleBar.setBackground(getResourceColor(R.color.text_green));
        // R.layout.yinshi_detail_item  适配器布局
        titleBar.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ShiWuSelectActivity.class));
            }
        });
        pictureBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, YinShiTuPianActivity.class));
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
