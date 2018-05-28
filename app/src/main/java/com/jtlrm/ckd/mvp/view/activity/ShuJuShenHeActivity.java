package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.mvp.view.adapter.ShuJuShenHeAdapter;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShuJuShenHeActivity extends TitleBarActivity {

    private final static String PILIANGE = "批量审核";
    private final static String CANCEL = "取消";
    @BindView(R.id.bottom)
    RelativeLayout bottmContainer;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ShuJuShenHeAdapter adapter;
    @BindView(R.id.tongguo)
    TextView tongguo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleText() {
        return "数据审核";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_shu_ju_shen_he);
    }

    @Override
    protected void initView() {
        setEditStatus(false);
        titleBar.tvRight.setVisibility(View.VISIBLE);
        adapter = new ShuJuShenHeAdapter();
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
        titleBar.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditStatus(!isEdite());
            }
        });
        tongguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ShenHeFalseActivity.class));
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(context, ShuJuShenHeDetailActivity.class));
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

    /**
     * 是否属于审核状态
     *
     * @return true 处于审核状态
     */
    public boolean isEdite() {
        if (CANCEL.equals(titleBar.tvRight.getText() + "")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置审核状态 true 处于审核中
     *
     * @param status
     */
    private void setEditStatus(boolean status) {
        if (status) {
            // 可编辑
            bottmContainer.setVisibility(View.VISIBLE);
            titleBar.tvRight.setText(CANCEL);
        } else {
            bottmContainer.setVisibility(View.GONE);
            titleBar.tvRight.setText(PILIANGE);
        }
        if (adapter != null) {
            adapter.setEdite(status);
        }
    }

    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.margin_background), 2, 2, -1);
    }
}
