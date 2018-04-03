package com.ys.yarc.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.ys.yarc.R;
import com.ys.yarc.base.acitvity.BaseActivity;
import com.ys.yarc.mvp.model.MainPresent;
import com.ys.yarc.mvp.view.api.IMainView;
import com.ys.yarc.mvp.view.fragment.HomeFragment;
import com.ys.yarc.mvp.view.fragment.PersonFragment;
import com.ys.yarc.mvp.view.fragment.RenCaiServiceFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainView, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_group)
    RadioGroup radioGroup;
    FragmentManager manager;
    HomeFragment homeFragment;
    RenCaiServiceFragment renCaiFragment;
    PersonFragment personFragment;
    private List<Fragment> fragments = new ArrayList<>();
    private int currentFragmentIndex = 0;
    MainPresent present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        present = new MainPresent(this);
    }

    @Override
    protected void obtainData() {
        FragmentTransaction ft = manager.beginTransaction();
        if (homeFragment == null) homeFragment = new HomeFragment();
        if (renCaiFragment == null) renCaiFragment = new RenCaiServiceFragment();
        if (personFragment == null) personFragment = new PersonFragment();
        fragments.add(homeFragment);
        fragments.add(renCaiFragment);
        fragments.add(personFragment);
        ft.add(R.id.main_container, fragments.get(currentFragmentIndex));
        ft.commit();
    }

    @Override
    protected void initEvent() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int temp = 0;
        switch (checkedId) {
            case R.id.main_group_button_1:
                temp = 0;    // 首页
                break;
            case R.id.main_group_button_2:
                temp = 1;    // 通知公告
                break;
            case R.id.main_group_button_3:
                temp = 2;    // 个人中心
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
