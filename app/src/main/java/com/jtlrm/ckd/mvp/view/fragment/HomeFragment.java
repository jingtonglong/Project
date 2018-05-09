package com.jtlrm.ckd.mvp.view.fragment;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.sdk.util.ImageUtil;
import com.base.sdk.widget.TitleBar;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.fragment.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_title)
    TitleBar titleBar;
    @BindView(R.id.home_banner)
    Banner banner;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        titleBar.tvMiddle.setText("CKD-Cloud医护");
        titleBar.tvMiddle.setTextColor(context.getResources().getColor(R.color.white));
        titleBar.imgRight.setImageResource(R.drawable.er_wei_ma_icon);
        titleBar.imgRight.setVisibility(View.VISIBLE);
        titleBar.setBackground(R.color.home_title_background);
    }

    @Override
    protected void obtainData() {
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(BANNER_ITEMS);
        banner.start();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initEvent() {
    }

    public static List<HotNewsFragment.BannerItem> BANNER_ITEMS = new ArrayList<HotNewsFragment.BannerItem>() {{
        add(new HotNewsFragment.BannerItem("最后的骑士", R.drawable.image_movie_header_48621499931969370));
        add(new HotNewsFragment.BannerItem("三生三世十里桃花", R.drawable.image_movie_header_12981501221820220));
        add(new HotNewsFragment.BannerItem("豆福传", R.drawable.image_movie_header_12231501221682438));
    }};


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageUtil.loadImage(imageView, ((HotNewsFragment.BannerItem) path).pic);
        }
    }
}
