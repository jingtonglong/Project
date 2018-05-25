package com.jtlrm.ckd.mvp.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.NewsEntity;

public class SuiFangPaiBanAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {
    public SuiFangPaiBanAdapter() {
        super(R.layout.suifang_paiban_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {

    }
}
