package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.mvp.view.adapter.AddSuiFangAdapter;
import com.jtlrm.ckd.mvp.view.adapter.SuiFangPaiBanAdapter;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddSuiFangPersonActivity extends TitleBarActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    AddSuiFangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected String getTitleText() {
        return "选择随访患者";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_add_sui_fang_person);
    }

    @Override
    protected void initView() {
        adapter = new AddSuiFangAdapter();
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
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.margin_background), 2, 20, -1);
    }
}
