package com.jtlrm.ckd.mvp.view.fragment;

import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;

import butterknife.BindView;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_title)
    TitleBar titleBar;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("CKD-Cloud医护");
        titleBar.imgRight.setImageResource(R.drawable.er_wei_ma_icon);
        titleBar.imgRight.setVisibility(View.VISIBLE);
        titleBar.setBackground(R.color.home_title_background);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
    }

}
