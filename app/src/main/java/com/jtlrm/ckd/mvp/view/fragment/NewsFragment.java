package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.view.KeyEvent;

import com.base.sdk.base.net.CommonObserver;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.jtlrm.ckd.entity.ListEntity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.entity.ResultData;
import com.jtlrm.ckd.mvp.model.NewsModel;
import com.jtlrm.ckd.mvp.view.adapter.NewsAdapter;

import butterknife.BindView;

/**
 * 新闻列表（此处不采用mvp模型，因为操作很少只需要加入数据层就行，去掉present层）
 */
public class NewsFragment extends BaseFragment {
    protected static final String ARG_PARAM1 = "model";
    private NewsModel newsModel;
    @BindView(R.id.news_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.news_refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int pageNum = 1;
    private int pageSize = 12;
    NewsAdapter adapter;

    public NewsFragment() {

    }

    /**
     * 传递数据模型
     *
     * @param newsModel
     * @return
     */
    public static NewsFragment newInstance(NewsModel newsModel) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, newsModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsModel = (NewsModel) getArguments().getSerializable(ARG_PARAM1);
        }
    }


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        adapter = new NewsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void obtainData() {
        loadData();
    }

    @Override
    protected void initEvent() {
        adapter.openLoadAnimation();
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                loadData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                loadData();
            }
        });
    }

    private void loadData() {
        newsModel.getNews(pageNum, pageSize, new CommonObserver<ResultData<ListEntity<NewsEntity>>>() {
            @Override
            public void onError(int errType, String errMessage) {
                showToast(errMessage);
                closeFrshorLoadmore();
            }

            @Override
            public void onResult(ResultData<ListEntity<NewsEntity>> data) {
                if (pageNum > 1) {
                    adapter.addData(data.getData().getList());
                } else {
                    adapter.replaceData(data.getData().getList());
                }
                if (data.getData().isHasNextPage()) {
                    smartRefreshLayout.setEnableLoadmore(true);
                } else {
                    smartRefreshLayout.setEnableLoadmore(false);
                }
                closeFrshorLoadmore();
            }
        }, lifecycleSubject);

    }


    /**
     * 关闭加载更多或者刷新
     */
    private void closeFrshorLoadmore() {
        if (smartRefreshLayout.isLoading()) {
            smartRefreshLayout.finishLoadmore();
        }
        if (smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
