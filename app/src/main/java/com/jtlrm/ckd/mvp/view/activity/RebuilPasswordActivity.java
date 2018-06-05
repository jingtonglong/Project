package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

import butterknife.BindView;

public class RebuilPasswordActivity extends TitleBarActivity {

    @BindView(R.id.password)
   public EditText password;
    @BindView(R.id.re_password)
    public EditText repassword;
    public  String passwordStr;
    public  String repasswordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "重置密码";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_rebuil_password);
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

    public void submit(View view) {
//        startActivity(new Intent(context, ));
    }

    public boolean passwordIsOk() {
        if (inputEmpty(password)) {
            showToast("请输入密码");
            return false;
        }
        if (inputEmpty(repassword)) {
            showToast("请确认密码");
            return false;
        }
        passwordStr = password.getText() + "";
        repasswordStr = repassword.getText() + "";
        if (!passwordStr.equals(repasswordStr)) {
            showToast("两次密码输入不一致");
            return false;
        }

        if (passwordStr.length() < 6) {
            showToast("密码至少六位");
            return false;
        }
        return true;
    }
}
