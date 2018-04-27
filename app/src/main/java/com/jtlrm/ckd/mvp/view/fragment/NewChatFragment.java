package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.base.sdk.base.adapter.TabFragmentPagerAdapter;
import com.base.sdk.base.entity.TabItemEntity;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.jtlrm.ckd.huanxin.MyContactListFragment;
import com.jtlrm.ckd.huanxin.MyConversationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在线随访
 */
public class NewChatFragment extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.tab_viewpager)
    ViewPager viewPager;
    public TabFragmentPagerAdapter adapter;
    List<TabItemEntity> tabItemEntities = new ArrayList<>();
    MyConversationFragment conversationFragment;
    MyContactListFragment contactListFragment;
    public NewChatFragment() {
        // Required empty public constructor
    }

    public static NewChatFragment newInstance() {
        NewChatFragment fragment = new NewChatFragment();
        return fragment;
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_new_chat;
    }

    @Override
    protected void initView() {
        adapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void obtainData() {
        conversationFragment = MyConversationFragment.newInstance();
        contactListFragment = MyContactListFragment.newInstance();
        tabItemEntities.add(new TabItemEntity("消息", conversationFragment));
        tabItemEntities.add(new TabItemEntity("通讯录", contactListFragment));
        adapter.setListContent(tabItemEntities);
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

    public void refresh() {
        if (conversationFragment != null) {
            conversationFragment.refresh();
            contactListFragment.refresh();
        }
    }

}
