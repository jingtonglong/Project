package com.base.sdk.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.sdk.R;


/**
 * 自定义titlebar
 */
public class TitleBar extends RelativeLayout {

	public ImageButton btLeft;
	public ImageButton btRight;
	public ImageButton btSearch;
	public ImageButton btPerson;
	public TextView tvMiddle;
	public TextView tvRight;
	public ImageView imgRight;
	public ImageView imgLeft;
	public RelativeLayout layout;
	public CheckBox cbRight;
//	public ImageView titleAPPLogo;
	public TitleBar(Context context) {
		super(context, null);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(getContext()).inflate(R.layout.layout_title_bar, this);
		btLeft = (ImageButton) findViewById(R.id.title_left_button);
		btRight = (ImageButton) findViewById(R.id.title_right_button);
		btSearch = (ImageButton) findViewById(R.id.title_search_button);
		btPerson = (ImageButton) findViewById(R.id.title_person_button);
		imgLeft = (ImageView) findViewById(R.id.title_left_image);
		tvMiddle = (TextView) findViewById(R.id.title_middle_text);
		tvRight = (TextView) findViewById(R.id.tv_title_right);
		imgRight = (ImageView) findViewById(R.id.title_right_image);
		layout = (RelativeLayout) findViewById(R.id.title_bar_relativelayout);
		cbRight = (CheckBox) findViewById(R.id.title_spinner_right_button);
//		titleAPPLogo = (ImageView) findViewById(R.id.iv_logo_app);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
		String title = ta.getString(R.styleable.TitleBar_tbTitle);
		if (!TextUtils.isEmpty(title)) {
			tvMiddle.setText(title);
		}
		String rightText = ta.getString(R.styleable.TitleBar_tbRightText);
//		if (!TextUtils.isEmpty(rightText)) {
//			tvRight.setText(rightText);
//			tvRight.setVisibility(View.VISIBLE);
//		}
		ta.recycle();
	}

	public void setBackground(Drawable drawable){
		layout.setBackgroundDrawable(drawable);
	}

	public void setBackground(int color){
		layout.setBackgroundColor(color);
	}
}
