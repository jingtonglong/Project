package com.jtlrm.ckd.huanxin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.hyphenate.chatuidemo.Constant;
import com.hyphenate.chatuidemo.conference.ConferenceActivity;
import com.hyphenate.chatuidemo.ui.ContactListFragment;
import com.hyphenate.chatuidemo.ui.GroupsActivity;
import com.hyphenate.chatuidemo.ui.NewFriendsMsgActivity;
import com.hyphenate.chatuidemo.ui.NewGroupActivity;
import com.hyphenate.chatuidemo.ui.PublicChatRoomsActivity;
import com.hyphenate.chatuidemo.ui.RobotsActivity;
import com.hyphenate.chatuidemo.widget.ContactItemView;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.jtlrm.ckd.entity.UserEntity;
import com.jtlrm.ckd.mvp.view.activity.MyWorkerActivity;
import com.jtlrm.ckd.util.ContactList.ContactListView;
import com.jtlrm.ckd.util.ContactList.InitialLetterUitl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 通讯录
 */
public class MyContactListFragment extends BaseFragment {

    @BindView(R.id.my_contact_list)
    ContactListView contactListView;
    protected ListView listView;
    public static MyContactListFragment newInstance() {
        MyContactListFragment fragment = new MyContactListFragment();
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_my_constact_list;
    }

    @Override
    protected void initView() {
        listView = contactListView.getListView();
        @SuppressLint("InflateParams") View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.contacts_header, null);
        HeaderItemClickListener clickListener = new HeaderItemClickListener();
        headerView.findViewById(R.id.my_group_item).setOnClickListener(clickListener);
        headerView.findViewById(R.id.my_create_group_item).setOnClickListener(clickListener);
        headerView.findViewById(R.id.my_worker_item).setOnClickListener(clickListener);
        listView.addHeaderView(headerView);
    }

    @Override
    protected void obtainData() {
        String[] user = new String[]{"啥都健康","山东矿机", "阿萨德技术的","按时大基地", "阿萨大所多","大撒旦", "大大","萨达", "而问题","送人头", "十多个","色胆如天", "色图","很反感", "我去","还没呢", "离开了","老客户"
                , "法国红酒","看就看", "任天野","发过火", "查询","中国", "阿萨德","发多少", "VB和","党费", "电热毯"
                ,"发过火", "热推","对象", "昆仑决","确", "撒地方","程序", "而"};
//        String[] user = new String[]{"sdf","we", "vcx","ad", "qwe","nbv", "we","sad", "qwe","ads", "bcv","wer", "as","lk", "jkl","ui", "uio","vnb"
//                , "zx","as", "cvx","sdf", "cxv","dfg", "dfg","yu", "fgh","fgh", "cvb"
//                ,"aqwe", "fsd","cxv", "jkj","ji", "iuo","ZX", "asd"};
        List<UserEntity> list = new ArrayList<>();
        for (String u: user) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(u);
            list.add(userEntity);
        }
        contactListView.init(list);
    }

    @Override
    protected void initEvent() {

    }

    protected class HeaderItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.my_group_item) {
                // 进入群聊列表页面
                startActivity(new Intent(getActivity(), GroupsActivity.class));
            } else if (id ==  R.id.my_worker_item) {
                // 我的同事
                startActivity(new Intent(getActivity(), MyWorkerActivity.class));
            } else if (id == R.id.my_create_group_item) {
                //进入创建群聊
                startActivity(new Intent(getActivity(), NewGroupActivity.class));
            }
        }

    }
}
