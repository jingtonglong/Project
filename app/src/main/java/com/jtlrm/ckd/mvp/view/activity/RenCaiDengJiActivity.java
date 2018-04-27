package com.jtlrm.ckd.mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.entity.RenCaiEntity;
import com.jtlrm.ckd.mvp.presenter.RenCaiDengJiPresent;
import com.jtlrm.ckd.mvp.view.adapter.RenCaiAdapter;
import com.jtlrm.ckd.mvp.view.api.IRenCaiDengJiView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 人才登记
 */
public class RenCaiDengJiActivity extends BaseActivity implements IRenCaiDengJiView {

    @BindView(R.id.rencai_title)
    TitleBar titleBar;
    @BindView(R.id.rencai_recyclerView)
    RecyclerView recyclerView;
    RenCaiAdapter adapter;
    View header;
    String[] labels = new String[] {"姓名:","性别:","学历:","手机:","身份证:","出身年月:","人才类型:","人才类别:","政治面貌:","婚姻状况:","健康状况:","政治面貌:", "参加工作时间:"};
    List<RenCaiEntity> renCaiEntities;
    RenCaiDengJiPresent present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_ren_cai_deng_ji);
    }

    @Override
    protected void initView() {
        present = new RenCaiDengJiPresent(this);
        adapter = new RenCaiAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        header = LayoutInflater.from(context).inflate(R.layout.rencaidengji_header, recyclerView, false);
        adapter.addHeaderView(header);
    }

    @Override
    protected void obtainData() {
        titleBar.tvMiddle.setText("个人信息修改");
        titleBar.imgLeft.setVisibility(View.VISIBLE);
        titleBar.imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        renCaiEntities = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            RenCaiEntity entity = new RenCaiEntity();
            if (i == 0) {
                entity.setIcon(R.drawable.rencai_name_icon);
            } else {
                entity.setIcon(R.drawable.rencai_other_icon);
            }
            entity.setLabel(labels[i]);
            renCaiEntities.add(entity);
        }
        adapter.addData(renCaiEntities);
    }

    @Override
    protected void initEvent() {

    }

}
