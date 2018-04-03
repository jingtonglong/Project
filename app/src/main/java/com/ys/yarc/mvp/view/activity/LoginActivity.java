package com.ys.yarc.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ys.yarc.R;
import com.ys.yarc.base.acitvity.BaseActivity;
import com.ys.yarc.mvp.presenter.LoginPresent;
import com.ys.yarc.mvp.view.api.ILoginView;

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
                loginPresent.login(usernameT.getText() + "", password.getText() + "");
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

    @Override
    public void loadUserName(String username) {
        usernameT.setText(username + "");
    }


    public void register(View view) {
        startActivity(new Intent(context, RegisterActivity.class));
    }
}
