package com.jtlrm.ckd.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.sdk.R;
import com.base.sdk.base.net.LifeCycleEvent;
import com.base.sdk.util.CommonUtil;
import com.base.sdk.util.ToastUtil;
import com.base.sdk.widget.loadlayout.LoadLayout;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;


/**
 * date：     2017/3/3
 * description Fragment基类
 * 继承后该类后，不需要再绑定ButterKnife。当fragment可见时才会进行初始化工作(懒加载)
 * 实现setContentLayout来设置并返回布局ID，
 * 实现initView来做视图相关的初始化，
 * 实现obtainData来做数据的初始化
 * 实现initEvent来做事件监听的初始化
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment {

    protected ImmersionBar mImmersionBar;
    //根布局视图
    private View mContentView;
    //用于butterknife解绑
    private Unbinder unbinder;
    //用于控制retrofit的生命周期，以便在destroy或其他状态时终止网络请求
    public PublishSubject<LifeCycleEvent> lifecycleSubject = PublishSubject.create();

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
    private android.app.ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        if (mContentView == null) {
            try {
                mContentView = inflater.inflate(setContentLayout(), container, false);
            } catch (Resources.NotFoundException e) {

            }
            if (mContentView == null) {
                throw new NullPointerException("根布局的id非法导致根布局为空,请检查后重试!");
            }
            unbinder = ButterKnife.bind(this, mContentView);
        }
        return mContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onFragmentVisiable();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isImmersionBarEnabled())
            initImmersionBar();
    }

    //设置并返回布局ID
    protected abstract int setContentLayout();

    //做视图相关的初始化
    protected abstract void initView();

    //来做数据的初始化
    protected abstract void obtainData();

    //做事件监听的初始化
    protected abstract void initEvent();

    public void onFragmentVisiable() {
        initView();
        initEvent();
        obtainData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(LifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(LifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(LifeCycleEvent.DESTROY);
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(getActivity());
        mImmersionBar.keyboardEnable(true).statusBarDarkFont(true, 0.2f).navigationBarWithKitkatEnable(false).init();
    }

    /**
     * 这个函数用于移除根视图
     * 因为已经有过父布局的View是不能再次添加到另一个新的父布局上面的
     */
    @Override
    public void onDestroyView() {
        if (mContentView != null) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        super.onDestroyView();
        //ButterKnife解绑
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 该函数可以Find一个被定义在XML中的根视图上的控件
     *
     * @param id 资源id
     * @return 这个id对应的控件
     */
    @CheckResult
    public View findViewById(@IdRes int id) {
        if (mContentView == null) {
            throw new NullPointerException("请检查你的根布局id合法性或view不为空后再调用此方法!");
        }
        return mContentView.findViewById(id);
    }

    /**
     * 显示进度
     */
    public void showLoadingDialog(String msg) {
        if (context != null) {
            if (pd == null) {
                pd = new android.app.ProgressDialog(context);
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

    /**
     * 隐藏输入法
     */
    public void hideInput() {
        View view = getActivity().getWindow().peekDecorView();
        CommonUtil.hideSoftInput(getContext(), view);
    }

    /**
     * 弹出提示框
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (!TextUtils.isEmpty(msg) && context != null) {
            ToastUtil.TextToast(context, msg);
        }
    }
}
