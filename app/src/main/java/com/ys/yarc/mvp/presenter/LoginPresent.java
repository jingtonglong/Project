package com.ys.yarc.mvp.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.base.present.BasePresenter;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.HuanXinApplication;
import com.hyphenate.chatuidemo.db.DemoDBManager;
import com.hyphenate.chatuidemo.ui.LoginActivity;
import com.hyphenate.chatuidemo.ui.MainActivity;
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
//        userModel.login(username, password, new CommonObserver<LoginResult>() {
//            @Override
//            public void onError(int errType, String errMessage) {
//                mIView.dismissLoadingDialog();
//                mIView.loginFail(errMessage);
//            }
//
//            @Override
//            public void onResult(LoginResult data) {
//                mIView.dismissLoadingDialog();
//                userHelper.setToken(data.getData());
//                userHelper.setUserInfo(data.getUserInfo());
//                mIView.loginSuccess();
//            }
//        }, mIView.getLifeSubject());
        loginHuanXin(username, password);
    }

    public void loginHuanXin(String username, String password) {
        // After logout，the DemoDB may still be accessed due to async callback, so the DemoDB will be re-opened again.
        // close it before login to make sure DemoDB not overlap
        DemoDBManager.getInstance().closeDB();

        // reset current user name before login
        DemoHelper.getInstance().setCurrentUserName(username);
        EMClient.getInstance().login(username, password, new EMCallBack() {

            @Override
            public void onSuccess() {
                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                // update current user's display name for APNs
                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
                        HuanXinApplication.currentUserNick.trim());
                if (!updatenick) {
                    Log.e("LoginActivity", "update current user nick fail");
                }
                // get user's info (this should be get from App's server or 3rd party service)
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                mIView.dismissLoadingDialog();
                mIView.loginSuccess();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
                mIView.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mIView.showToast(message + ", 聊天服务器登录失败，聊天功能会收到影响");
                        mIView.loginSuccess();
                    }
                });
            }
        });

    }
}
