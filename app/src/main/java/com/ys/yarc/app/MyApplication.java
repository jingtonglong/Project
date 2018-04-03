package com.ys.yarc.app;


import com.base.sdk.app.BaseApplication;
import com.ys.yarc.net.RetrofitUtil;

/**
 * date:    2017/9/13
 * description: 全局初始化操作
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitUtil.init(this);//初始化retrofit
    }

}
