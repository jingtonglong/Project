package com.base.sdk.app;

import android.app.Application;

import com.base.sdk.util.log.LogUtil;

/**
 * Created by Administrator on 2018/3/2/002.
 */

public class BaseApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        //内存泄露检测
//        if (LeakCanary.isInAnalyzerProcess(this)) return;
//        refWatcher = LeakCanary.install(this);

//        EventBusUtil.openIndex();//开启Index加速
    }


    /**
     * 内存泄露检测
     */
//    private RefWatcher refWatcher;
//
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }
}
