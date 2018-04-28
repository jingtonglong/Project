package com.jtlrm.ckd.app;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.sdk.util.log.LogUtil;
import com.hyphenate.chatuidemo.HuanXinApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.jtlrm.ckd.net.RetrofitUtil;
import com.jtlrm.ckd.util.EventBusUtil;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * date:    2017/9/13
 * description: 全局初始化操作
 */

public class MyApplication extends HuanXinApplication {
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
       super.onCreate();
        RetrofitUtil.init(this);//初始化retrofit
        LogUtil.init(true);//初始化Log打印，true表示显示打印，false表示不显示打印
        EventBusUtil.openIndex();
        if (LeakCanary.isInAnalyzerProcess(this)) return;
        refWatcher =  LeakCanary.install(this);
       // initJpush();
        // bugly start// 发布时候设置成false
        CrashReport.initCrashReport(getApplicationContext(), "be9cedcad1", true);
        // bugly end
    }

    /**
     * 初始化极光推送
     */
    private void initJpush() {
//        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);            // 初始化 JPush
//        // 指定定制的 Notification Layout
//        CustomPushNotificationBuilder builder = new
//                CustomPushNotificationBuilder(this,
//                R.layout.notification_layout,
//                R.id.icon,
//                R.id.title,
//                R.id.text);
//        // 指定最顶层状态栏小图标
//        builder.statusBarDrawable = R.mipmap.logo_small;
//        // 指定下拉状态栏时显示的通知图标
//        builder.layoutIconDrawable = R.mipmap.ic_launcher;
//        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }


}
