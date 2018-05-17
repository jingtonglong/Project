package com.jtlrm.ckd.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import java.util.List;

import butterknife.BindViews;

public class YingYangBaoGaoAddActivity extends BaseActivity implements View.OnClickListener{
    @BindViews({R.id.expand_1, R.id.expand_2, R.id.expand_3, R.id.expand_4})
    List<ImageView> expandArrows;
    @BindViews({R.id.expand_container_1, R.id.expand_container_2, R.id.expand_container_3, R.id.expand_container_4})
    List<LinearLayout> expandContainer;
    @BindViews({R.id.expand_linear_1, R.id.expand_linear_2, R.id.expand_linear_3, R.id.expand_linear_4})
    List<RelativeLayout> expandRelative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_ying_yang_bao_gao_add);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        for (RelativeLayout imageView : expandRelative) {
            imageView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expand_linear_1:
                updateExpandContent(0);
                break;
            case R.id.expand_linear_2:
                updateExpandContent(1);
                break;
            case R.id.expand_linear_3:
                updateExpandContent(2);
                break;
            case R.id.expand_linear_4:
                updateExpandContent(3);
                break;
        }
    }

    public void updateExpandContent(int position) {
        if (expandContainer.get(position).isShown()) {
            expandContainer.get(position).setVisibility(View.GONE);
            expandArrows.get(position).setImageResource(R.drawable.arrow_down);
        } else {
            expandContainer.get(position).setVisibility(View.VISIBLE);
            expandArrows.get(position).setImageResource(R.drawable.arrow_up);
        }
    }
}
