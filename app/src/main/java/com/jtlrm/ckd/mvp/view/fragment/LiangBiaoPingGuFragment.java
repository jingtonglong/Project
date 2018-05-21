package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.jtlrm.ckd.mvp.view.activity.LiangBiaoPingGuSubListActivity;

import java.util.List;

import butterknife.BindViews;

/**
 * 量表评估
 */
public class LiangBiaoPingGuFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    @BindViews({R.id.liangbiao_1, R.id.liangbiao_2, R.id.liangbiao_3, R.id.liangbiao_4, R.id.liangbiao_5, R.id.liangbiao_6})
    List<RelativeLayout> items;

    public LiangBiaoPingGuFragment() {
    }


    public static LiangBiaoPingGuFragment newInstance(String param1) {
        LiangBiaoPingGuFragment fragment = new LiangBiaoPingGuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_liang_biao_ping_gu;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        for (RelativeLayout relativeLayout : items) {
            relativeLayout.setOnClickListener(this);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, LiangBiaoPingGuSubListActivity.class);
        switch (v.getId()) {
            case R.id.liangbiao_1:
                break;
            case R.id.liangbiao_2:
                break;
            case R.id.liangbiao_3:
                break;
            case R.id.liangbiao_4:
                break;
            case R.id.liangbiao_5:
                break;
            case R.id.liangbiao_6:
                break;
        }
    }
}
