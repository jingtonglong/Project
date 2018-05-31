package com.jtlrm.ckd.base.acitvity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.widget.EditText;

import com.base.sdk.base.api.PositiveOrCancelInterface;
import com.base.sdk.base.net.LifeCycleEvent;
import com.base.sdk.util.ToastUtil;
import com.base.sdk.util.log.LogUtil;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.jtlrm.ckd.R;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;


/**
 * description: Activity的基类，包含Activity栈管理，状态栏/导航栏颜色设置，销毁时取消网络请求等
 * 子类需要进行ButterKnife绑定
 * 注：不将BaseActivity放入sdkmodule是因为bButterKnife使用问题
 */

public abstract class BaseActivity extends AbstractActivity implements IBaseActivity {

    //用于控制retrofit的生命周期，以便在destroy或其他状态时终止网络请求
    public PublishSubject<LifeCycleEvent> lifecycleSubject = PublishSubject.create();
    ImmersionBar mImmersionBar;
    //该方法用于提供lifecycleSubject（相当于实现了IBaseView中的getLifeSubject抽象方法）。
    //方便Presenter中直接通过IBaseView获取lifecycleSubject，而不用每次都作为参数传递过去
    public PublishSubject<LifeCycleEvent> getLifeSubject() {
        return lifecycleSubject;
    }

    //一般的rxjava使用场景下，控制Observable的生命周期
    public <T> ObservableTransformer<T, T> controlLife(final LifeCycleEvent event) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<LifeCycleEvent> lifecycleObservable = lifecycleSubject.filter(new Predicate<LifeCycleEvent>() {
                    @Override
                    public boolean test(LifeCycleEvent lifeCycleEvent) throws Exception {
                        //当生命周期为event状态时，发射事件
                        return lifeCycleEvent.equals(event);
                    }
                }).take(1);
                return upstream.takeUntil(lifecycleObservable);
            }
        };
    }

    protected Activity context;
    private ProgressDialog pd;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        init();
        setContentLayout();//由具体的activity实现，设置内容布局ID
        ButterKnife.bind(this);
        initBarColor();//初始化状态栏/导航栏颜色，需在设置了布局后再调用
        initView();//由具体的activity实现，做视图相关的初始化
        obtainData();//由具体的activity实现，做数据的初始化
        initEvent();//由具体的activity实现，做事件监听的初始化
    }

    private void init() {

    }

    protected void initBarColor() {
        mImmersionBar = ImmersionBar.with(getActivity());
        mImmersionBar.keyboardEnable(true).statusBarDarkFont(true, 0.2f).navigationBarWithKitkatEnable(false).init();
    }


    //设置状态栏、导航栏颜色，第二个参数控制透明度，布局内容不占据状态栏空间
    public void setBarColor(int statusColor, float statusAlpha, int textColor) {
        ImmersionBar.with(this).statusBarColor(statusColor)
                .statusBarAlpha(statusAlpha)
                .statusBarDarkFont(true, 0.2f) // 判断当前设备支不支持状态栏字体设置为黑色
                .flymeOSStatusBarFontColor(textColor)
                .fitsSystemWindows(true) // 不用沉浸式
                .keyboardEnable(true) //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .init();
    }


    @Override
    protected void onPause() {
        lifecycleSubject.onNext(LifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(LifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(LifeCycleEvent.DESTROY);
        dissMissDialog();
        dismissLoadingDialog();
        ImmersionBar.with(this).destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getResourceColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(getResources(), colorId, null);
    }

    @Override
    public String getResourceString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    @Override
    public String getResourceString(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    @Override
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return ResourcesCompat.getDrawable(getResources(), id, null);
    }

    @Override
    public void onLowMemory() {
        LogUtil.e("内存不足");
        super.onLowMemory();
    }

    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtil.TextToast(getContext(), msg);
        }
    }

    /**
     * 显示进度
     */
    public void showLoadingDialog(String msg) {
        if (context != null) {
            if (pd == null) {
                pd = new ProgressDialog(context);
            }
            pd.setMessage(msg);
            pd.setCanceledOnTouchOutside(false);
            pd.show();
        }
    }

    /**
     * 关闭进度
     */
    public void dismissLoadingDialog() {
        if (context != null && pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }


    public Activity getActivity() {
        return activity;
    }

    /**
     * 显示确认
     *
     * @param msg
     * @param result
     */
    NormalDialog dialog;

    protected void showCustomDialog(String msg, final PositiveOrCancelInterface result) {
        if (context != null) {
            dialog = new NormalDialog(context);
            dialog.content(msg).show();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                            result.result(false);
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                            result.result(true);
                        }
                    });
        }
    }

    protected void dissMissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean notEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean inputEmpty(EditText editText) {
        String content = (editText.getText() + "").trim();
        if (notEmpty(content)) {
            return false;
        } else {
            return true;
        }
    }


}
