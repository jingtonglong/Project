package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.base.sdk.base.adapter.TabFragmentPagerAdapter;
import com.base.sdk.base.entity.TabItemEntity;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.jtlrm.ckd.mvp.model.NewsModel;
import com.jtlrm.ckd.mvp.view.activity.MessageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.tab_viewpager)
    ViewPager viewPager;
    @BindView(R.id.home_notice)
    ImageView notice;
    public TabFragmentPagerAdapter adapter;
    List<TabItemEntity> tabItemEntities = new ArrayList<>();

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        adapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void obtainData() {
        tabItemEntities.add(new TabItemEntity("推荐", HotNewsFragment.newInstance(new NewsModel())));
        tabItemEntities.add(new TabItemEntity("动态", NewsFragment.newInstance(new NewsModel(12))));
        tabItemEntities.add(new TabItemEntity("政策", NewsFragment.newInstance(new NewsModel(15))));
        tabItemEntities.add(new TabItemEntity("活动", NewsFragment.newInstance(new NewsModel(11))));
        adapter.setListContent(tabItemEntities);
    }

    @Override
    protected void initEvent() {
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MessageActivity.class));
            }
        });
    }

}
