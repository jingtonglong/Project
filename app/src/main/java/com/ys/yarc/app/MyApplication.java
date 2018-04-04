package com.ys.yarc.app;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.sdk.util.log.LogUtil;
import com.hyphenate.chatuidemo.HuanXinApplication;
import com.ys.yarc.net.RetrofitUtil;

/**
 * date:    2017/9/13
 * description: 全局初始化操作
 */

public class MyApplication extends HuanXinApplication {

    @Override
    public void onCreate() {
        MultiDex.install(this);
       super.onCreate();
        RetrofitUtil.init(this);//初始化retrofit
        LogUtil.init(true);//初始化Log打印，true表示显示打印，false表示不显示打印
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
