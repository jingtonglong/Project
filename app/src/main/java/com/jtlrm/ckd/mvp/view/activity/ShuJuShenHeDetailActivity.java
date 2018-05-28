package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.mvp.view.adapter.ShuJuShenDetailHeAdapter;
import com.jtlrm.ckd.mvp.view.adapter.ShuJuShenHeAdapter;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShuJuShenHeDetailActivity extends TitleBarActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ShuJuShenDetailHeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "生化数据详情";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_shu_ju_shen_he_detail);
    }

    @Override
    protected void initView() {
        titleBar.imgRight.setImageResource(R.drawable.picture_browse);
        titleBar.imgRight.setVisibility(View.VISIBLE);
        adapter = new ShuJuShenDetailHeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(createItemDecoration());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void obtainData() {
        loadData();
    }

    @Override
    protected void initEvent() {
        titleBar.imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, YinShiTuPianActivity.class));
            }
        });
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    final List<NewsEntity> list = new ArrayList<>();
                    for (int i = 0; i < 12; i++) {
                        NewsEntity newsEntity = new NewsEntity();
                        newsEntity.setTitle("title" + i);
                        list.add(newsEntity);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addData(list);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.margin_background), 2, 2, -1);
    }
}
