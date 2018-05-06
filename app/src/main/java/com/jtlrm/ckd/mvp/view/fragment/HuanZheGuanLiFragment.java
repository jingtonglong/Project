package com.jtlrm.ckd.mvp.view.fragment;


import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;

import butterknife.BindView;

/**
 * 患者管理
 */
public class HuanZheGuanLiFragment extends BaseFragment {
    @BindView(R.id.huanzhe_guanli_title)
    TitleBar titleBar;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_huan_zhe_guan_li;
    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("患者管理");
        titleBar.tvRight.setText("新患者建档");
        titleBar.tvRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
