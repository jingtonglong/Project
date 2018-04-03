package com.base.sdk.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


import com.base.sdk.base.entity.TabItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/4/004.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {


    private List<TabItemEntity> list = new ArrayList<>();
   // Fragment[] fragments = new Fragment[] {NoticeFragment.newInstance(0),NoticeFragment.newInstance(1)};
    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public void setListContent(List<TabItemEntity> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (list.get(position).getFragment()== null) {
            Log.e("Test",">>>>>>>>>>>>");
        }
        return list.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }
}


