package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.entity.UserEntity;
import com.jtlrm.ckd.util.ContactList.PicContactsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PicWorkerActivity extends BaseActivity{
    @BindView(R.id.pic_worker_view)
    PicContactsView picContactsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pic_worker);
    }

    @Override
    protected void initView() {
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
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
