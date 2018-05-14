package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;

import butterknife.BindView;

/**
 * 饮食数据
 */
public class YinShiDataFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";


    private String mParam1;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public YinShiDataFragment() {

    }

    public static YinShiDataFragment newInstance(String param1) {
        YinShiDataFragment fragment = new YinShiDataFragment();
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
        return R.layout.fragment_yin_shi_data;
    }

    @Override
    protected void initView() {
    // 头文件    R.layout.yinshi_data_header;
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
