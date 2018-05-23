package com.jtlrm.ckd.mvp.view.adapter;


import android.widget.ImageView;

import com.base.sdk.util.ImageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.config.UrlConstants;
import com.jtlrm.ckd.entity.NewsEntity;


/**
 * Created by Administrator on 2018/3/12/012.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {
    public NewsAdapter() {
        super(R.layout.adapter_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        // 显示一张图
        helper.setVisible(R.id.one_image_layout, true);
        helper.setGone(R.id.three_image_layout, false);
        helper.setText(R.id.one_title, item.getTitle() + "");
      //  helper.setText(R.id.one_hits, item.getHits() + "");
       // ImageUtil.loadImage((ImageView) helper.getView(R.id.one_image), UrlConstants.PROJECT_URL + images[0]);
    }
}
