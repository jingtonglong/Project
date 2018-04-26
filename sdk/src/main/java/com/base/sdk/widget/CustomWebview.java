package com.base.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * 禁用webview的双击缩放功能
 * Created by Administrator on 2018/3/8/008.
 */

public class CustomWebview extends WebView {
    public CustomWebview(Context context) {
        super(context);
    }

    public CustomWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    long last_time = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long current_time = System.currentTimeMillis();
                long d_time = current_time - last_time;
                System.out.println(d_time);;
                if (d_time < 3000) {
                    last_time = current_time;
                    return true;
                } else {
                    last_time = current_time;
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}

