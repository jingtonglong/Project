package com.ys.yarc.mvp.view.adapter;


import android.widget.ImageView;

import com.base.sdk.util.ImageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ys.yarc.R;
import com.ys.yarc.entity.RenCaiEntity;

/**
 * Created by Administrator on 2018/3/28/028.
 */

public class RenCaiAdapter extends BaseQuickAdapter<RenCaiEntity, BaseViewHolder> {

    public RenCaiAdapter() {
        super(R.layout.rencai_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, RenCaiEntity item) {
        ImageUtil.loadImage((ImageView) helper.getView(R.id.rencai_item_icon), item.getIcon());
        helper.setText(R.id.rencai_item_label, item.getLabel());
        helper.setText(R.id.rencai_item_input, item.getInput());

    }
}

