package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import butterknife.BindView;

/**
 * 聊天个人信息
 */
@Route(path = "/person/info/chat")
public class ChatPersonInfoActivity extends BaseActivity {
    @BindView(R.id.chat_person_info_title)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_chat_person_info);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void initView() {
        Long s = getIntent().getLongExtra("userId", 0l);
        titleBar.tvMiddle.setText("详细资料");
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
