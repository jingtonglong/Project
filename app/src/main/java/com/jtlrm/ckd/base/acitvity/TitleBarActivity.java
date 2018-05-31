package com.jtlrm.ckd.base.acitvity;

import android.os.Bundle;
import android.view.View;

import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;

import butterknife.BindView;

public abstract class TitleBarActivity extends BaseActivity{
    
    @BindView(R.id.title_bar)
    public TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleBar();
    }

    protected void initTitleBar() {
        titleBar.btLeft.setVisibility(View.VISIBLE);
        titleBar.btLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.tvMiddle.setText(getTitleText());
    }

    protected abstract String getTitleText();
}
