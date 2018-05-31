package com.jtlrm.ckd.mvp.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.base.sdk.base.net.CommonObserver;
import com.base.sdk.base.present.BasePresenter;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.HuanXinApplication;
import com.hyphenate.chatuidemo.db.DemoDBManager;
import com.jtlrm.ckd.entity.LoginResult;
import com.jtlrm.ckd.entity.UserEntity;
import com.jtlrm.ckd.mvp.model.UserModel;
import com.jtlrm.ckd.mvp.model.dao.UserHelper;
import com.jtlrm.ckd.mvp.view.api.ILoginView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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
    }

    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mIView.loginFail("用户名与密码都不能为空！");
            return;
        }
        mIView.showLoadingDialog("登陆中");
        userModel.login(username, password, new Observer<LoginResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                userHelper.setLogin(loginResult);
                updateInfo();
            }

            @Override
            public void onError(Throwable e) {
                mIView.dismissLoadingDialog();
                mIView.loginFail("登录请求异常");
            }

            @Override
            public void onComplete() {

            }
        }, mIView.getLifeSubject());
        // loginHuanXin(username, password);
    }

    private void updateInfo() {
        userModel.getUserInfo(new CommonObserver<UserEntity>() {
            @Override
            public void onError(int errType, String errMessage) {
                mIView.showToast("个人信息获取失败，" + errMessage);
                mIView.dismissLoadingDialog();
                mIView.loginSuccess();
            }

            @Override
            public void onResult(UserEntity data) {
                userHelper.setUserInfo(data);
                mIView.dismissLoadingDialog();
                mIView.loginSuccess();
            }
        }, mIView.getLifeSubject());
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
