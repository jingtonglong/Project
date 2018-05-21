package com.jtlrm.ckd.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import butterknife.BindView;
import butterknife.BindViews;

public class HomeQueryHuanZheActivity extends BaseActivity implements View.OnClickListener{

    @BindViews(R.id.back)
    ImageView back;
    @BindView(R.id.search_content)
    EditText inputContent;
    @BindView(R.id.no_data)
    ImageView noData;
    @BindView(R.id.yes_data)
    RelativeLayout yesData;
    int type;
    public static void goSearch(Context context, int type){
        Intent intent = new Intent(context, HomeQueryHuanZheActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", 0);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_home_query_huan_zhe);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
