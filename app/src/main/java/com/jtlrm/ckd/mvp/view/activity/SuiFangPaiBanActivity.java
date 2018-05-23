package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.base.sdk.base.net.CommonObserver;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.entity.ListEntity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.entity.ResultData;
import com.jtlrm.ckd.mvp.view.adapter.NewsAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 随访排班
 */
public class SuiFangPaiBanActivity extends BaseActivity implements  CalendarView.OnDateSelectedListener,
        CalendarView.OnYearChangeListener{

    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.news_refreshLayout)
//    SmartRefreshLayout smartRefreshLayout;
    private int pageNum = 1;
    private int pageSize = 12;
    NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_sui_fang_pai_ban);
    }

    @Override
    protected void initView() {
        initCalendar();
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
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnDateSelectedListener(this);
        adapter.openLoadAnimation();
//        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                pageNum++;
//                loadData();
//            }
//
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                pageNum = 1;
//                loadData();
//            }
//        });
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    final List<NewsEntity> list = new ArrayList<>();
                    for (int i = 0; i < 12;i++) {
                        NewsEntity newsEntity = new NewsEntity();
                        newsEntity.setTitle("title" + i);
                        list.add(newsEntity);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeFrshorLoadmore();
                            if (pageNum == 1) {
                                adapter.replaceData(list);
                            } else {
                                adapter.addData(list);
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 关闭加载更多或者刷新
     */
    private void closeFrshorLoadmore() {
//        if (smartRefreshLayout.isLoading()) {
//            smartRefreshLayout.finishLoadmore();
//        }
//        if (smartRefreshLayout.isRefreshing()) {
//            smartRefreshLayout.finishRefresh();
//        }
    }

    private void initCalendar() {
        List<Calendar> schemes = new ArrayList<>();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        schemes.add(getSchemeCalendar(year, month, mCalendarView.getCurDay(), 0, ""));
        mCalendarView.setSchemeDate(schemes);
    }

    @SuppressWarnings("all")
    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onYearChange(int year) {

    }

    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {

    }
}
