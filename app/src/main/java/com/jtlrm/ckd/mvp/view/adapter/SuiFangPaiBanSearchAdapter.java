package com.jtlrm.ckd.mvp.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.NewsEntity;

public class SuiFangPaiBanSearchAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {
    public SuiFangPaiBanSearchAdapter() {
        super(R.layout.suifang_paiban_search_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {

    }
}

