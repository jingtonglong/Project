package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.util.ValidateUtils;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.entity.LoginResult;
import com.jtlrm.ckd.entity.YanZhengMaEntity;
import com.jtlrm.ckd.mvp.model.UserModel;
import com.jtlrm.ckd.mvp.model.dao.UserHelper;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ForgetPasswordActivity extends TitleBarActivity {

    @BindView(R.id.phone)
    EditText phoneE;
    @BindView(R.id.yanzhengma)
    EditText yanZhengMaE;
    @BindView(R.id.get_yanzhengma)
    TextView getYanZhengMaE;
    String phone;
    UserModel userModel;
    String yanZhengMa;
    boolean canSend = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "忘记密码";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_forget_password);
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
        getYanZhengMaE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canSend) {
                    return;
                }
                phone = phoneE.getText() + "";
                if (ValidateUtils.Mobile(phone)) {
                    if (!notEmpty(UserHelper.getInstance(context).getToken())) {
                        showLoadingDialog("加载中");
                        updateToken();
                    } else {
                        sendMessage();
                    }

                } else {
                    showToast("请输入正确的手机号");
                }
            }
        });
    }

    private void updateToken() {
        userModel.getToken(new Observer<LoginResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                UserHelper.getInstance(context).setLogin(loginResult);
                sendMessage();
            }

            @Override
            public void onError(Throwable e) {
                dismissLoadingDialog();
                showToast("获取token失败");
            }

            @Override
            public void onComplete() {

            }
        }, lifecycleSubject);
    }

    private void sendMessage() {
        userModel.sendForgetMessage(phone, new CommonObserver<YanZhengMaEntity>() {
            @Override
            public void onError(int errType, String errMessage) {
                dismissLoadingDialog();
                showToast(errMessage);
                canSend = true;
            }

            @Override
            public void onResult(YanZhengMaEntity data) {
                dismissLoadingDialog();
                showToast("已发送验证，注意查收");
                yanZhengMa = data.getCode();
                countDownTimer.start();
                canSend = false;
            }
        }, lifecycleSubject);
    }

    CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getYanZhengMaE.setText((millisUntilFinished / 1000) + "s后重新获取");
        }

        @Override
        public void onFinish() {
            canSend = true;
            getYanZhengMaE.setText("获取验证码");
        }
    };

    public void next(View view){
        if (!notEmpty(yanZhengMa)) {
            showToast("请获取验证码");
            return;
        }
        if (inputEmpty(yanZhengMaE)) {
            showToast("请输入验证码");
            return;
        }
        if (!yanZhengMa.equals(yanZhengMaE.getText() + "")) {
            showToast("验证码输入有误");
            return;
        }
        RebuilPasswordActivity.goSetting(context, phone);
        finish();
    }
}
