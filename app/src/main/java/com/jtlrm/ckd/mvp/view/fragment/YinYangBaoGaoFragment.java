package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.jtlrm.ckd.mvp.view.activity.YingYangBaoGaoAddActivity;
import com.jtlrm.ckd.mvp.view.activity.YingYangBaoGaoDetailActivity;

import butterknife.BindView;

/**
 * 营养报告
 */
public class YinYangBaoGaoFragment extends BaseFragment {
   private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    @BindView(R.id.tiaozhuan)
    TextView tiaozhuan;
    public YinYangBaoGaoFragment() {
        // Required empty public constructor
    }


    public static YinYangBaoGaoFragment newInstance(String param1) {
        YinYangBaoGaoFragment fragment = new YinYangBaoGaoFragment();
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
        return R.layout.fragment_yin_yang_bao_gao;
    }

    @Override
    protected void initView() {
        //R.layout.yingyang_baogao_data_header;
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, YingYangBaoGaoAddActivity.class));
            }
        });
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
