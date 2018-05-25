package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;
import com.jtlrm.ckd.entity.NewsEntity;
import com.jtlrm.ckd.mvp.view.adapter.SuiFangPaiBanAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 随访排班
 */
public class SuiFangPaiBanActivity extends BaseActivity implements CalendarView.OnDateSelectedListener,
        CalendarView.OnYearChangeListener, SwipeItemClickListener {

    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.select_date)
    TextView selectText;
    private int pageNum = 1;
    private int pageSize = 12;
    SuiFangPaiBanAdapter adapter;
    Calendar mCalendar; // 当前选中的时间

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
        adapter = new SuiFangPaiBanAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(createItemDecoration());
        recyclerView.setSwipeItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void obtainData() {
        loadData();
        initListHeaderAndFooter();
    }


    @Override
    protected void initEvent() {
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnDateSelectedListener(this);
        adapter.openLoadAnimation();

    }

    /**
     * 初始化Foot和header
     */
    private void initListHeaderAndFooter() {
        View header = LayoutInflater.from(context).inflate(R.layout.suifang_paiban_header, null);
        adapter.addHeaderView(header);
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
        if (mCalendar == null || mCalendar != calendar) {
            mCalendar = calendar;
            selectText.setText(calendar.getYear() + "-" + calendar.getMonth());
        }
    }

    @Override
    public void onItemClick(View itemView, int position) {

    }

    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.line_color));
    }

    public void goSearch(View view) {
        startActivity(new Intent(context, SuiFangPaiBanSearchActivity.class));
    }
}
