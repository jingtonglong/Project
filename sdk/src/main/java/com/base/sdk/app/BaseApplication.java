package com.base.sdk.app;

import android.app.Application;
import android.content.Context;

import com.base.sdk.util.log.LogUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2018/3/2/002.
 */

public class BaseApplication extends Application {


    private String logoPath;
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //内存泄露检测
        if (LeakCanary.isInAnalyzerProcess(this)) return;
        refWatcher =  LeakCanary.install(this);
        LogUtil.init(true);//初始化Log打印，true表示显示打印，false表示不显示打印
    }

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }
    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public static void setInstance(BaseApplication instance) {
        BaseApplication.instance = instance;
    }
}
