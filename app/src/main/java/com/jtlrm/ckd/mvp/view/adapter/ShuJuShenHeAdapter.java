package com.jtlrm.ckd.mvp.view.adapter;

import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.entity.NewsEntity;

public class ShuJuShenHeAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {

    private boolean isEdite = false;

    public ShuJuShenHeAdapter() {
        super(R.layout.shuju_shenhe_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        CheckBox checkBox = helper.getView(R.id.check);
        if (isEdite) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }

    /**
     * 设置是否是审核状态
     *
     * @param isEdite
     */
    public void setEdite(boolean isEdite) {
        this.isEdite = isEdite;
        notifyDataSetChanged();
    }
}

