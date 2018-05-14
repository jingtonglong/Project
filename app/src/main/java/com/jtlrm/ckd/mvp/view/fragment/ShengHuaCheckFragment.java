package com.jtlrm.ckd.mvp.view.fragment;

import android.os.Bundle;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;

/**
 * 生化检查
 */
public class ShengHuaCheckFragment extends BaseFragment {
     private static final String ARG_PARAM1 = "param1";



    private String mParam1;


    public ShengHuaCheckFragment() {

    }


    public static ShengHuaCheckFragment newInstance(String param1) {
        ShengHuaCheckFragment fragment = new ShengHuaCheckFragment();
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
        return R.layout.fragment_sheng_hua_check;
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
