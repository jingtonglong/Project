package com.base.sdk.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 应用更新新版
 * Created by hp on 2017/5/27.
 */

public class UpdateAppUtil {
    ProgressDialog pdCheckVersion;
    Context context;

    private static final int OK_CODE = 1; // 传输正常
    private static final int ERROR_CODE = 2; // 传输错误
    private static final int DOWNLOAD_OK = 3; // 传输正常
    private static final int DOWMLOAD_ERROR = 4; // 传输错误

    public static UpdateAppUtil getInstance(Context context){
        UpdateAppUtil updateAppUtil = new UpdateAppUtil();
        updateAppUtil.context = context;
        return updateAppUtil;
    }



    private void closeProgress(){
        if (pdCheckVersion != null && pdCheckVersion.isShowing()){
            pdCheckVersion.dismiss();
        }
    }




    Handler updateHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case OK_CODE:
                    // 网络请求正确
                    closeProgress();
                    break;
                case ERROR_CODE:
                    // 网络请求出错
                    closeProgress();
                    break;
                case DOWNLOAD_OK:
                    installApk((File) msg.obj);
                    break;
                case DOWMLOAD_ERROR:
                    ToastUtil.TextToast(context,"下载失败");
                    break;
            }
            return false;
            }
        });


    /**
     * 下载apk
     * @param filePath
     */
    public void downLoadApk(final String filePath) {
        if (context == null){
            return;
        }
        final ProgressDialog pd;
        pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("下载中");
        pd.setProgressNumberFormat("");
        pd.setCancelable(false);
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    File file = getFileFromServer(filePath,pd);
                    Thread.sleep(3*1000);
                    message.obj = file;
                    message.what = DOWNLOAD_OK;
                    updateHandler.sendMessage(message);
                }catch (Exception e){
                    message.what = DOWMLOAD_ERROR;
                    updateHandler.sendMessage(message);
                }
                pd.dismiss();
            }
        }).start();
    }

    //安装apk
    protected void installApk(File file) {
        if (!file.exists()){
            return;
        }
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private File getFileFromServer(String path, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(path);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            if (pd != null){
                pd.setMax(conn.getContentLength());
            }
            InputStream is = conn.getInputStream();
            String name = System.currentTimeMillis()+"";
            File file = new File(Environment.getExternalStorageDirectory(), name+".apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;
            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                //获取当前下载量
                if (pd != null){
                    pd.setProgress(total);
                }
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        }
        else{
            return null;
        }
    }

}
