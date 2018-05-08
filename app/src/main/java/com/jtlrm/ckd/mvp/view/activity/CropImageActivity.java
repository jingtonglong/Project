package com.jtlrm.ckd.mvp.view.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.isseiaoki.simplecropview.util.Logger;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.BaseActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class CropImageActivity extends BaseActivity {
    private static final String KEY_SOURCE_URI = "SourceUri";
    public static final int REQUEST_CODE = 96;
    @BindView(R.id.cropImageView)
    CropImageView mCropView;
    Uri image;
    private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;
    public static void crop(Context context, Uri image) {
        Intent intent = new Intent(context, CropImageActivity.class);
        intent.putExtra("image", image);
        ((Activity)context).startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // restore data
            image = savedInstanceState.getParcelable(KEY_SOURCE_URI);
        } else {
            image = getIntent().getParcelableExtra("image");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_crop_image);
    }

    @Override
    protected void initView() {
        if (image != null) {
            mCropView.setCropMode(CropImageView.CropMode.CIRCLE);
            mCropView.load(image).execute(new LoadCallback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }


    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    public void cropView(View view) {
        showLoadingDialog("保存中");
        mCropView.crop(image).execute(new CropCallback() {
            @Override
            public void onSuccess(Bitmap cropped) {
                mCropView.save(cropped)
                        .compressFormat(mCompressFormat)
                        .execute(createSaveUri(), mSaveCallback);
            }

            @Override
            public void onError(Throwable e) {
                dismissLoadingDialog();
                cancel();
            }
        });
    }

    private final SaveCallback mSaveCallback = new SaveCallback() {
        @Override
        public void onSuccess(Uri outputUri) {
            dismissLoadingDialog();
            startResultActivity(outputUri);
        }

        @Override
        public void onError(Throwable e) {
            dismissLoadingDialog();
            cancel();
        }
    };



    public void cancelView(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private void cancel() {
        setResult(Activity.RESULT_OK);
    }

    public void startResultActivity(Uri uri) {
        if (isFinishing()) return;
        Intent intent = new Intent();
        intent.setData(uri);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save data
        outState.putParcelable(KEY_SOURCE_URI, mCropView.getSourceUri());
    }

    public Uri createSaveUri() {
        return createNewUri(getContext(), mCompressFormat);
    }

    public static Uri createNewUri(Context context, Bitmap.CompressFormat format) {
        long currentTimeMillis = System.currentTimeMillis();
        Date today = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String title = dateFormat.format(today);
        String dirPath = getDirPath();
        String fileName = "scv" + title + "." + getMimeType(format);
        String path = dirPath + "/" + fileName;
        File file = new File(path);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/" + getMimeType(format));
        values.put(MediaStore.Images.Media.DATA, path);
        long time = currentTimeMillis / 1000;
        values.put(MediaStore.MediaColumns.DATE_ADDED, time);
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, time);
        if (file.exists()) {
            values.put(MediaStore.Images.Media.SIZE, file.length());
        }

        ContentResolver resolver = context.getContentResolver();
        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Logger.i("SaveUri = " + uri);
        return uri;
    }

    public static String getDirPath() {
        String dirPath = "";
        File imageDir = null;
        File extStorageDir = Environment.getExternalStorageDirectory();
        if (extStorageDir.canWrite()) {
            imageDir = new File(extStorageDir.getPath() + "/simplecropview");
        }
        if (imageDir != null) {
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }
            if (imageDir.canWrite()) {
                dirPath = imageDir.getPath();
            }
        }
        return dirPath;
    }

    public static String getMimeType(Bitmap.CompressFormat format) {
        Logger.i("getMimeType CompressFormat = " + format);
        switch (format) {
            case JPEG:
                return "jpeg";
            case PNG:
                return "png";
        }
        return "png";
    }

}
