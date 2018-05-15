package com.jtlrm.ckd.mvp.view.adapter;


import android.widget.ImageView;

import com.base.sdk.util.ImageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;


public class PictureBottomAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PictureBottomAdapter() {
        super(R.layout.picture_image_bottom_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageUtil.loadRoundImage((ImageView) helper.getView(R.id.picture), item);

    }
}

