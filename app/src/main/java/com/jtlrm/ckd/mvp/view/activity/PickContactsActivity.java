package com.jtlrm.ckd.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.hyphenate.chatuidemo.ui.GroupsActivity;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.entity.UserEntity;
import com.jtlrm.ckd.huanxin.MyContactListFragment;
import com.jtlrm.ckd.util.ContactList.PicContactsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 选择群成员
 */
public class PickContactsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.pic_contacts_view)
    PicContactsView picContactsView;
    ListView listView;
    @BindView(R.id.check_all)
    CheckBox allCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pick_contacts);
    }

    @Override
    protected void initView() {
        listView = picContactsView.getListView();
        @SuppressLint("InflateParams") View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.pic_contacts_header, null);
        HeaderItemClickListener clickListener = new HeaderItemClickListener();
        headerView.findViewById(R.id.my_worker_item).setOnClickListener(clickListener);
        listView.addHeaderView(headerView);
    }

    @Override
    protected void obtainData() {
        String[] user = new String[]{"啥都健康", "山东矿机", "阿萨德技术的", "按时大基地", "阿萨大所多", "大撒旦", "大大", "萨达", "而问题", "送人头", "十多个", "色胆如天", "色图", "很反感", "我去", "还没呢", "离开了", "老客户"
                , "法国红酒", "看就看", "任天野", "发过火", "查询", "中国", "阿萨德", "发多少", "VB和", "党费", "电热毯"
                , "发过火", "热推", "对象", "昆仑决", "确", "撒地方", "程序", "而"};
        List<UserEntity> list = new ArrayList<>();
        for (String u : user) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(u);
            list.add(userEntity);
        }
        picContactsView.init(list);
    }

    @Override
    protected void initEvent() {
        allCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_all:
                if (allCheck.isChecked()) {
                    picContactsView.selectAll();
                } else {
                    picContactsView.clearAll();
                }
                break;
        }
    }

    protected class HeaderItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.my_worker_item) {
                // 我的同事
                startActivity(new Intent(getActivity(), PicWorkerActivity.class));
            }
        }

    }
}
