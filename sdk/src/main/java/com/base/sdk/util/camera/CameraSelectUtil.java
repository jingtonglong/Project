package com.base.sdk.util.camera;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.base.sdk.R;
import com.base.sdk.util.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片选择器 （使用单列的目的是为了试红米手机调用该类不会回收）
 * Created by hp on 2016/10/17.
 */

public class CameraSelectUtil {
    public static final int REQUEST_CODE_CAMERA = 1;
    public static final int REQUEST_CODE_FOR_IMAGE = 2;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int MEDIA_TYPE_CROP = 3;

    Context context;
    public Uri fileUri;

    public CameraSelectUtil(Context context) {
        this.context = context;
    }

    /**
     * 仅仅使用相机
     */
    public void selectPic() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if (fileUri != null) {
            ((Activity) context).startActivityForResult(intent, REQUEST_CODE_CAMERA);
        } else {
            ToastUtil.TextToast(context, "请重试");
        }
    }

    /**
     * 使用相机和相册
     */
    public void selectPicBoth() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_select_head_pic, null);
        ListView listView = (ListView) contentView.findViewById(R.id.list);
        SelectPicAdapter adapter = new SelectPicAdapter(context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_CAMERA);
                        dialog.dismiss();
                        break;
                    case 1:
                        intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_FOR_IMAGE);
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
        dialog.setContentView(contentView);
        dialog.show();
    }

    public String getPicturePath(Uri uri) {
        String path = "";
        try {
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                ContentResolver resolver = context.getContentResolver();
                String[] proj = {MediaStore.Images.Media.DATA};
                //好像是Android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = resolver.query(uri, proj, null, null, null);
                // 这个是获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                //将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                //最后根据索引值获取图片路径
                path = cursor.getString(column_index);
            } else {
                path = uri.getPath();
            }

        } catch (Exception e) {
            path = "";
        }
        return path;
    }


    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "YunShuApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("YunShuApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else if (type == MEDIA_TYPE_CROP) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_CROP_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }


}
