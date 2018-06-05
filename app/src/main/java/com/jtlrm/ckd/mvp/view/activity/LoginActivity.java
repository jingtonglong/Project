package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.mvp.presenter.LoginPresent;
import com.jtlrm.ckd.mvp.view.api.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.user_name)
    EditText usernameT;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.submit)
    Button submit;
    LoginPresent loginPresent;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        loginPresent = new LoginPresent(this);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresent.login((usernameT.getText() + "").trim(), (password.getText() + "").trim());
            }
        });
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void loginFail(String msg) {
        showToast(msg);
    }


    public void register(View view) {
        startActivity(new Intent(context, CompleteInfoActivity.class));
    }
}
