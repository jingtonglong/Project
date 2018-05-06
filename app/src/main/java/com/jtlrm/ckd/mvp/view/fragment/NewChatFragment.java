package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.base.sdk.base.adapter.TabFragmentPagerAdapter;
import com.base.sdk.base.entity.TabItemEntity;
import com.base.sdk.widget.TitleBar;
import com.hyphenate.chatuidemo.ui.NewGroupActivity;
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
    @BindView(R.id.new_chat_title)
    TitleBar titleBar;
    public NewChatFragment() {
        // Required empty public constructor
    }

    public static NewChatFragment newInstance() {
        NewChatFragment fragment = new NewChatFragment();
        return fragment;
    }

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
        titleBar.tvMiddle.setText("在线随访");
        titleBar.tvRight.setVisibility(View.VISIBLE);
        titleBar.tvRight.setText("发起群聊");
        titleBar.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NewGroupActivity.class));
            }
        });
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
          //  contactListFragment.refresh();
        }
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


}
