package com.base.sdk.util;

import android.content.Context;
import android.widget.ImageView;

import com.base.sdk.R;
import com.base.sdk.app.GlideApp;
import com.bumptech.glide.Glide;

/**
 * 图片加载类
 * Created by Administrator on 2018/3/26/026.
 */

public class ImageUtil {

    public static void loadImage(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext()).load(url).placeholder(R.drawable.picture_default_blue).error(R.drawable.picture_default_blue).fitCenter().into(imageView);
    }

    public static void loadImage(ImageView imageView, int id) {
        GlideApp.with(imageView.getContext()).load(id).placeholder(R.drawable.picture_default_blue).error(R.drawable.picture_default_blue).fitCenter().into(imageView);
    }

    public static void loadImageGif(ImageView imageView, int id) {
        GlideApp.with(imageView.getContext()).load(id).into(imageView);
    }
}
