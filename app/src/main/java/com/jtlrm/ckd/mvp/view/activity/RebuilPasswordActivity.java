package com.jtlrm.ckd.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.base.sdk.base.net.CommonObserver;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.mvp.model.UserModel;

import butterknife.BindView;

public class RebuilPasswordActivity extends TitleBarActivity {

    @BindView(R.id.password)
    public EditText password;
    @BindView(R.id.re_password)
    public EditText repassword;
    public String passwordStr;
    public String repasswordStr;
    String phone;
    UserModel userModel;
    public static void goSetting(Context context, String phone) {
        Intent intent = new Intent(context, RebuilPasswordActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        phone = getIntent().getStringExtra("phone");
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
        userModel = new UserModel();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    public void submit(View view) {
//        startActivity(new Intent(context, ));
        if (passwordIsOk()) {
            userModel.rebuildPassword(phone, passwordStr, new CommonObserver<String>() {
                @Override
                public void onError(int errType, String errMessage) {
                    showToast(errMessage);
                }

                @Override
                public void onResult(String data) {
                    showToast("重置成功");
                    finish();
                }
            }, lifecycleSubject);
        }
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
