package com.jtlrm.ckd.mvp.view.api;

import com.base.sdk.base.api.IBaseView;
import com.jtlrm.ckd.entity.UserEntity;

/**
 * Created by Administrator on 2018/3/1/001.
 */

public interface ILoginView extends IBaseView {
    void loginSuccess();
    void loginFail(String msg);
    void loadUserName(String username);
}
