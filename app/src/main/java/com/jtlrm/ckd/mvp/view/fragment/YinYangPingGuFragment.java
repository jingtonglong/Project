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
 * 营养评估
 */
public class YinYangPingGuFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    public YinYangPingGuFragment() {
        // Required empty public constructor
    }


    public static YinYangPingGuFragment newInstance(String param1) {
        YinYangPingGuFragment fragment = new YinYangPingGuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yin_yang_ping_gu, container, false);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_yin_yang_ping_gu;
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
    public void onDetach() {
        super.onDetach();

    }


}
