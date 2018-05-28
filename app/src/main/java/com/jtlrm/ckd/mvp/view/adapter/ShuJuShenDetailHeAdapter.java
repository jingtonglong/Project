package com.jtlrm.ckd.mvp.view.adapter;

import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.NewsEntity;

public class ShuJuShenDetailHeAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {


    public ShuJuShenDetailHeAdapter() {
        super(R.layout.shujue_shenhe_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {

    }

}

