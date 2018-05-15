package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.mvp.view.fragment.LiangBiaoPingGuFragment;
import com.jtlrm.ckd.mvp.view.fragment.ShengHuaCheckFragment;
import com.jtlrm.ckd.mvp.view.fragment.YinShiDataFragment;
import com.jtlrm.ckd.mvp.view.fragment.YinYangBaoGaoFragment;
import com.jtlrm.ckd.mvp.view.fragment.YinYangPingGuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HuanZheManagerActivity extends TitleBarActivity implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragments = new ArrayList<>();
    FragmentManager manager;
    @BindView(R.id.huanzhe_group)
    RadioGroup radioGroup;
    YinShiDataFragment yinShiDataFragment;// 饮食数据
    ShengHuaCheckFragment shengHuaCheckFragment; // 生化检查
    YinYangPingGuFragment yinYangPingGuFragment; // 营养评估
    LiangBiaoPingGuFragment liangBiaoPingGuFragment; // 量表评估
    YinYangBaoGaoFragment yinYangBaoGaoFragment; // 营养报告
    private int currentFragmentIndex = 0;
    @BindView(R.id.basic_manager)
    ImageView basicManager;
    @BindView(R.id.basic_info)
    LinearLayout basicInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "患者管理";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_huan_zhe_manager);
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        String userId = "";
        if (yinShiDataFragment == null) yinShiDataFragment = YinShiDataFragment.newInstance(userId);
        if (shengHuaCheckFragment == null)
            shengHuaCheckFragment = ShengHuaCheckFragment.newInstance(userId);
        if (yinYangPingGuFragment == null)
            yinYangPingGuFragment = YinYangPingGuFragment.newInstance(userId);
        if (liangBiaoPingGuFragment == null)
            liangBiaoPingGuFragment = LiangBiaoPingGuFragment.newInstance(userId);
        if (yinYangBaoGaoFragment == null)
            yinYangBaoGaoFragment = YinYangBaoGaoFragment.newInstance(userId);
        fragments.add(yinShiDataFragment);
        fragments.add(shengHuaCheckFragment);
        fragments.add(yinYangPingGuFragment);
        fragments.add(liangBiaoPingGuFragment);
        fragments.add(yinYangBaoGaoFragment);
        ft.add(R.id.main_container, fragments.get(currentFragmentIndex));
        ft.commit();

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        radioGroup.setOnCheckedChangeListener(this);
        basicManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBasic();
            }
        });
    }

    private void showBasic() {
        if (basicInfo != null) {
            if (basicInfo.isShown()) {
                basicManager.setImageResource(R.drawable.arrow_buttom);
                basicInfo.setVisibility(View.GONE);
            } else {
                basicManager.setImageResource(R.drawable.arrow_top);
                basicInfo.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int temp = 0;
        switch (checkedId) {
            case R.id.huanzhe_group_button_1:
                temp = 0;
                break;
            case R.id.huanzhe_group_button_2:
                temp = 1;
                break;
            case R.id.huanzhe_group_button_3:
                temp = 2;
                break;
            case R.id.huanzhe_group_button_4:
                temp = 3;
                break;
            case R.id.huanzhe_group_button_5:
                temp = 4;
                break;
        }
        if (temp == currentFragmentIndex) {
            return;
        }
        Fragment fragment = fragments.get(temp);
        FragmentTransaction ft = manager.beginTransaction();
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(R.id.main_container, fragment);
        }
        ft.hide(fragments.get(currentFragmentIndex));
        currentFragmentIndex = temp;
        ft.commit();
    }
}
