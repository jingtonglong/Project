package com.ys.yarc.mvp.presenter;

import android.text.TextUtils;

import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.base.present.BasePresenter;
import com.ys.yarc.entity.LoginResult;
import com.ys.yarc.entity.ResultData;
import com.ys.yarc.entity.UserEntity;
import com.ys.yarc.mvp.model.UserModel;
import com.ys.yarc.mvp.model.dao.UserHelper;
import com.ys.yarc.mvp.view.api.ILoginView;


/**
 * Created by Administrator on 2018/3/1/001.
 */

public class LoginPresent extends BasePresenter<ILoginView> {

    UserModel userModel;
    UserHelper userHelper;

    public LoginPresent(ILoginView iLoginView) {
        super(iLoginView);
        this.userModel = new UserModel();
        userHelper = UserHelper.getInstance(iLoginView.getContext());
        if (notEmpty(userHelper.getUserInfo().getUsername())) {
            iLoginView.loadUserName(userHelper.getUserInfo().getUsername());
        }
    }

    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mIView.loginFail("用户名与密码都不能为空！");
            return;
        }
        mIView.showLoadingDialog("登陆中");
        userModel.login(username, password, new CommonObserver<LoginResult>() {
            @Override
            public void onError(int errType, String errMessage) {
                mIView.dismissLoadingDialog();
                mIView.loginFail(errMessage);
            }

            @Override
            public void onResult(LoginResult data) {
                mIView.dismissLoadingDialog();
                userHelper.setToken(data.getData());
                userHelper.setUserInfo(data.getUserInfo());
                mIView.loginSuccess();
            }
        }, mIView.getLifeSubject());
    }
}
