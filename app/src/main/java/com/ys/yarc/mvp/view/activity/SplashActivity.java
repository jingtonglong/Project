package com.ys.yarc.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.DemoHelper;
import com.hyphenate.chatuidemo.ui.*;
import com.hyphenate.util.EasyUtils;
import com.ys.yarc.base.acitvity.BaseActivity;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    private final static long DELAY = 3000;  // 界面延时

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentLayout() {

    }

    @Override
    protected void initView() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        window.setAttributes(params);
        // 初始化环信帮助类
        DemoHelper.getInstance().initHandler(this.getMainLooper());
        if (DemoHelper.getInstance().isLoggedIn()) {
            // auto login mode, make sure all group and conversation is loaed before enter the main screen
            long start = System.currentTimeMillis();
            EMClient.getInstance().chatManager().loadAllConversations();
            EMClient.getInstance().groupManager().loadAllGroups();
            long costTime = System.currentTimeMillis() - start;

            String topActivityName = EasyUtils.getTopActivityName(EMClient.getInstance().getContext());
            if (topActivityName != null && (topActivityName.equals(VideoCallActivity.class.getName()) || topActivityName.equals(VoiceCallActivity.class.getName()))) {
                // nop
                // avoid main screen overlap Calling Activity
            } else {
                delayToActivity(DELAY - costTime, MainActivity.class);
            }

        } else {
            delayToActivity(DELAY, LoginActivity.class); // 进入登录界面
        }
    }

    /**
     * 延迟时间进入界面
     *
     * @param time
     */
    private void delayToActivity(Long time, final Class<?> clazz) {
        if (time < 1000) {
            // 当延时时间小于一秒 直接进入界面，性能和体验综合考虑
            Intent intent = new Intent(SplashActivity.this, clazz);
            startActivity(intent);
            finish();
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, clazz);
                    startActivity(intent);
                    finish();
                }
            }, time);
        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
