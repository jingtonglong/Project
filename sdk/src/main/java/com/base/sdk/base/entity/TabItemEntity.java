package com.base.sdk.base.entity;

import android.support.v4.app.Fragment;

/**
 * tab子项
 * Created by Administrator on 2018/1/4/004.
 */

public class TabItemEntity {
    private String title;
    private Fragment fragment;

    public TabItemEntity() {
    }

    public TabItemEntity(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
