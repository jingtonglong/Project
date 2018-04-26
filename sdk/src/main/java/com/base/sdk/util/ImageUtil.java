package com.base.sdk.util;

import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.base.sdk.R;
import com.base.sdk.app.GlideApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片加载类
 * Created by Administrator on 2018/3/26/026.
 */

public class ImageUtil {

    public static void loadImage(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext()).load(url).fitCenter().into(imageView);
    }

    public static void loadImageSizeNews(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext()).load(url).placeholder(R.drawable.no_banner).error(R.drawable.no_banner).override(230,164).centerCrop().into(imageView);
    }

    public static void loadImage(ImageView imageView, int id) {
        GlideApp.with(imageView.getContext()).load(id).fitCenter().into(imageView);
    }

    public static void loadImageGif(ImageView imageView, int id) {
        GlideApp.with(imageView.getContext()).load(id).into(imageView);
    }

    /**
     * 将图片转换成Base64编码的字符串
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data,Base64.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
