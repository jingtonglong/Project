package com.jtlrm.ckd.mvp.view.adapter;

import android.widget.ImageView;

import com.base.sdk.util.ImageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.config.UrlConstants;

import java.util.List;

public class PictureTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PictureTopAdapter() {
        super(R.layout.picture_image_top_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageUtil.loadRoundImage((ImageView) helper.getView(R.id.picture), item);
    }
}
