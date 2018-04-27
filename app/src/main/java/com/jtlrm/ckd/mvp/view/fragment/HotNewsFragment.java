package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.base.sdk.util.ImageUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.mvp.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 热点新闻
 * Created by Administrator on 2018/3/27/027.
 */

public class HotNewsFragment extends NewsFragment {

    /**
     * 传递数据模型
     *
     * @param newsModel
     * @return
     */
    public static NewsFragment newInstance(NewsModel newsModel) {
        HotNewsFragment fragment = new HotNewsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, newsModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        super.initView();
        //添加Header
        View header = LayoutInflater.from(context).inflate(R.layout.roll_pager_news_header, recyclerView, false);
        Banner banner = (Banner) header;
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(BANNER_ITEMS);
        banner.start();
        adapter.addHeaderView(banner);

    }

    public static class BannerItem {

        public int pic;
        public String title;

        public BannerItem() {
        }

        public BannerItem(String title, int pic) {
            this.pic = pic;
            this.title = title;
        }


    }

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.drawable.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.drawable.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.drawable.image_movie_header_12231501221682438));
    }};

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageUtil.loadImage(imageView, ((BannerItem) path).pic);
        }
    }
}
