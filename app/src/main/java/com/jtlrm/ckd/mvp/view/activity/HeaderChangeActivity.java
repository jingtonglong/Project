package com.jtlrm.ckd.mvp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.base.sdk.util.ImageUtil;
import com.base.sdk.util.camera.CameraSelectUtil;
import com.jtlrm.ckd.R;
import com.jtlrm.ckd.base.acitvity.TitleBarActivity;

import butterknife.BindView;

public class HeaderChangeActivity extends TitleBarActivity {
    CameraSelectUtil cameraSelectUtil;
    String imageUrl;
    @BindView(R.id.header)
    ImageView imageHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected String getTitleText() {
        return "个人头像";
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_header_change);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            if (requestCode == CropImageActivity.REQUEST_CODE) {
                setImage(data.getData());
            } else {
                if (cameraSelectUtil != null) {
                    Uri image = null;
                    if (CameraSelectUtil.REQUEST_CODE_CAMERA == requestCode) {
                        image = cameraSelectUtil.fileUri;
                    }
                    if (CameraSelectUtil.REQUEST_CODE_FOR_IMAGE == requestCode) {
                        image = data.getData();
                    }
                    if (image != null) {
                        CropImageActivity.crop(context, image);
                    }
                }
            }
        }
    }

    private void setImage(Uri image) {
        imageUrl = image.toString();
        ImageUtil.loadCircleImage(imageHeader, imageUrl);
    }



    @Override
    protected void initView() {
        titleBar.imgRight.setImageResource(R.drawable.title_more);
        titleBar.imgRight.setVisibility(View.VISIBLE);
        titleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cameraSelectUtil == null) {
                    cameraSelectUtil = new CameraSelectUtil(context);
                }
                cameraSelectUtil.selectPicBoth();
            }
        });
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
