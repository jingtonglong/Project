package com.jtlrm.ckd.util.ContactList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.base.sdk.util.DensityUtil;
import com.hyphenate.easeui.widget.EaseContactList;
import com.hyphenate.easeui.widget.EaseSidebar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class ContactListView extends RelativeLayout {
    public ContactListView(Context context) {
        super(context);
        init(context, null);
    }

    public ContactListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ContactListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected static final String TAG = EaseContactList.class.getSimpleName();

    protected Context context;
    protected ListView listView;
    protected ContactAdapter adapter;
    protected List<UserEntity> contactList;
    protected EaseSidebar sidebar;

    protected int primaryColor;
    protected int primarySize;
    protected float marginTop;
    protected boolean showSiderBar;
    protected Drawable initialLetterBg;

    static final int MSG_UPDATE_LIST = 0;
    protected int initialLetterColor;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UPDATE_LIST:
                    if (adapter != null) {
                        adapter.clear();
                        adapter.addAll(new ArrayList<UserEntity>(contactList));
                        adapter.notifyDataSetChanged();
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ContactListView);
        primaryColor = ta.getColor(R.styleable.ContactListView_jtlListPrimaryTextColor, 0);
        primarySize = ta.getDimensionPixelSize(R.styleable.ContactListView_jtlListPrimaryTextSize, 0);
        showSiderBar = ta.getBoolean(R.styleable.ContactListView_jtlListShowSiderBar, true);
        initialLetterBg = ta.getDrawable(R.styleable.ContactListView_jtlListInitialLetterBg);
        initialLetterColor = ta.getColor(R.styleable.ContactListView_jtlListInitialLetterColor, 0);
        marginTop = ta.getDimension(R.styleable.ContactListView_jtlSlideBarTopMargin, 0);
        ta.recycle();

        LayoutInflater.from(context).inflate(com.hyphenate.easeui.R.layout.ease_widget_contact_list, this);
        listView = (ListView) findViewById(com.hyphenate.easeui.R.id.list);
        sidebar = (EaseSidebar) findViewById(com.hyphenate.easeui.R.id.sidebar);
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) sidebar.getLayoutParams();
        layoutParams.setMargins(0, DensityUtil.dp2px(context, marginTop), 0, 0);
        sidebar.setLayoutParams(layoutParams);
        if (!showSiderBar)
            sidebar.setVisibility(View.GONE);
    }

    /*
     * init view
     */
    public void init(List<UserEntity> contactList) {
        InitialLetterUitl.sortList(contactList);
        this.contactList = contactList;
        adapter = getAdapter();
        adapter.setPrimaryColor(primaryColor).setPrimarySize(primarySize).setInitialLetterBg(initialLetterBg)
                .setInitialLetterColor(initialLetterColor);
        listView.setAdapter(adapter);
        if (showSiderBar) {
            sidebar.setListView(listView);
        }
    }

    protected ContactAdapter getAdapter() {
        return  new ContactAdapter(context, 0, new ArrayList<UserEntity>(contactList));
    }


    public void refresh() {
        Message msg = handler.obtainMessage(MSG_UPDATE_LIST);
        handler.sendMessage(msg);
    }

    public void filter(CharSequence str) {
        adapter.getFilter().filter(str);
    }

    public ListView getListView() {
        return listView;
    }

    public void setShowSiderBar(boolean showSiderBar) {
        if (showSiderBar) {
            sidebar.setVisibility(View.VISIBLE);
        } else {
            sidebar.setVisibility(View.GONE);
        }
    }

}
