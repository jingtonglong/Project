package com.jtlrm.ckd.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
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
import java.util.Date;
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
    @BindView(R.id.add_person)
    LinearLayout addPerson;
    private int pageNum = 1;
    private int pageSize = 12;
    SuiFangPaiBanAdapter adapter;
    Calendar mCalendar; // 当前选中的时间
    private TimePickerView pvCustomLunar;
    TextView info;

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
        initLunarPicker();
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
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddSuiFangPersonActivity.class));
            }
        });
        adapter.openLoadAnimation();
        adapter.setMenuListener(new SuiFangPaiBanAdapter.MenuListener() {
            @Override
            public void click(int position) {
                showDate("张三", "201811111");
            }
        });
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
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.margin_background), 2, 20, -1);
    }

    public void goSearch(View view) {
        startActivity(new Intent(context, SuiFangPaiBanSearchActivity.class));
    }

    public void showDate(String huanzhe, String date) {
        if (info != null && pvCustomLunar != null) {
            info.setText(Html.fromHtml("患者" + huanzhe + "的随访日期从<font color= '#FF8B00'>" + date + "</font><br>更改到:"));
            pvCustomLunar.show();
        }
    }

    /**
     * 农历时间已扩展至 ： 1900 - 2100年
     */
    private void initLunarPicker() {
        java.util.Calendar selectedDate = java.util.Calendar.getInstance();//系统当前时间
        java.util.Calendar startDate = java.util.Calendar.getInstance();
        startDate.set(2000, 1, 23);
        java.util.Calendar endDate = java.util.Calendar.getInstance();
        endDate.set(2100, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.suifang_date_select, new CustomListener() {
                    @Override
                    public void customLayout(final View v) {
                        info = (TextView) v.findViewById(R.id.info);
                        TextView ok = (TextView) v.findViewById(R.id.queren);
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.GRAY)
                .build();
    }
}
