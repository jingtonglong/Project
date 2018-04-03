package com.ys.yarc.mvp.view.adapter;


import android.widget.ImageView;

import com.base.sdk.util.ImageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ys.yarc.R;
import com.ys.yarc.config.UrlConstants;
import com.ys.yarc.entity.NewsEntity;


/**
 * Created by Administrator on 2018/3/12/012.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsEntity, BaseViewHolder> {
    public NewsAdapter() {
        super(R.layout.adapter_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        String[] images = item.getImage().split(";");
        if (images != null && images.length > 2) {
            // 显示三张图
            helper.setVisible(R.id.one_image_layout, false);
            helper.setGone(R.id.three_image_layout, true);
            helper.setText(R.id.three_title, item.getTitle() + "");
            ImageUtil.loadImage((ImageView) helper.getView(R.id.three_image_1), UrlConstants.PROJECT_URL + images[0]);
            ImageUtil.loadImage((ImageView) helper.getView(R.id.three_image_2), UrlConstants.PROJECT_URL + images[1]);
            ImageUtil.loadImage((ImageView) helper.getView(R.id.three_image_3), UrlConstants.PROJECT_URL + images[2]);
        } else {
            // 显示一张图
            helper.setVisible(R.id.one_image_layout, true);
            helper.setGone(R.id.three_image_layout, false);
            helper.setText(R.id.one_title, item.getTitle() + "");
            helper.setText(R.id.one_hits, item.getHits() + "");
            ImageUtil.loadImage((ImageView) helper.getView(R.id.one_image), UrlConstants.PROJECT_URL + images[0]);
        }
    }
}
