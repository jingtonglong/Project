package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.base.sdk.util.CommonUtil;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.mvp.view.adapter.SuiFangPaiBanSearchAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class SuiFangPaiBanSearchActivity extends BaseActivity implements SwipeItemClickListener {

    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    SuiFangPaiBanSearchAdapter adapter;
    @BindView(R.id.search_content)
    EditText searchContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_sui_fang_pai_ban_search);
    }

    @Override
    protected void initView() {
        adapter = new SuiFangPaiBanSearchAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(createItemDecoration());
        recyclerView.setSwipeItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        searchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    showLoadingDialog("搜索中");
                    CommonUtil.hideSoftInput(context,searchContent);
                    loadData();
                }
                return false;
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
                            dismissLoadingDialog();
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
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.line_color));
    }

    @Override
    public void onItemClick(View itemView, int position) {

    }
}
