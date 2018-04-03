package com.ys.yarc.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.base.sdk.widget.TitleBar;
import com.ys.yarc.R;
import com.ys.yarc.base.acitvity.BaseActivity;
import com.ys.yarc.mvp.model.dao.UserHelper;

import butterknife.BindView;

public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.person_info_title)
    TitleBar titleBar;
    @BindView(R.id.person_info_name)
    EditText nameE;
    @BindView(R.id.person_info_phone)
    EditText phoneE;
    @BindView(R.id.person_info_email)
    EditText emailE;
    @BindView(R.id.person_info_submit)
    Button submit;
    UserHelper userHelper = UserHelper.getInstance(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_person_info);
    }

    @Override
    protected void initView() {
        initTitle();
        setEdit(false);
    }

    @Override
    protected void obtainData() {
        nameE.setText(userHelper.getUserInfo().getName() + "");
        phoneE.setText(userHelper.getUserInfo().getPhone() + "");
        if (userHelper.getUserInfo().getEmail() != null) {
            emailE.setText(userHelper.getUserInfo().getEmail() + "");
        } else {
            emailE.setText("");
        }
    }

    private void initTitle() {
        titleBar.tvMiddle.setText("个人信息修改");
        titleBar.imgLeft.setVisibility(View.VISIBLE);
        titleBar.imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.tvRight.setText("修改");
        titleBar.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEdit(true);
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    /**
     * s设置是否可以编辑
     *
     * @param isDo
     */
    public void setEdit(boolean isDo) {
        if (isDo) {
            nameE.setEnabled(true);
            phoneE.setEnabled(true);
            emailE.setEnabled(true);
            submit.setVisibility(View.VISIBLE);
            titleBar.tvRight.setVisibility(View.GONE);
        } else {
            nameE.setEnabled(false);
            phoneE.setEnabled(false);
            emailE.setEnabled(false);
            submit.setVisibility(View.GONE);
            titleBar.tvRight.setVisibility(View.VISIBLE);
        }
    }
}
