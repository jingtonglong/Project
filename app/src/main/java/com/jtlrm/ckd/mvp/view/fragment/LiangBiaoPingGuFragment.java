package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;

/**
 * 量表评估
 */
public class LiangBiaoPingGuFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


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

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
