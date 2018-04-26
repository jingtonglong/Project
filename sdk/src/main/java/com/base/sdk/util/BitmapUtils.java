package com.base.sdk.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class BitmapUtils {

	public static Bitmap narrowBitmap(String uri) {
		Bitmap bmp;
		try{
			 bmp = BitmapFactory.decodeFile(uri);
			return bmp;
		}catch (OutOfMemoryError err){
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 4; //为压缩4倍
			bmp = BitmapFactory.decodeFile(uri, opts);
			return bmp;
		}
	}

	/**
	 * 图片转成string
	 *
	 * @param bitmap
	 * @return
	 */
	public static String convertIconToString(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] appicon = baos.toByteArray();// 转为byte数组
		return Base64.encodeToString(appicon, Base64.DEFAULT);

	}

	/**
	 * string转成bitmap
	 *
	 * @param st
	 */
	public static Bitmap convertStringToIcon(String st)
	{
		// OutputStream out;
		Bitmap bitmap = null;
		try
		{
			// out = new FileOutputStream("/sdcard/aa.jpg");
			byte[] bitmapArray;
			bitmapArray = Base64.decode(st, Base64.DEFAULT);
			bitmap =
					BitmapFactory.decodeByteArray(bitmapArray, 0,
							bitmapArray.length);
			// bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			return bitmap;
		}
		catch (Exception e)
		{
			return null;
		}
	}

		// 图片加载的缓存工具类，安卓自带的方法
		public static BitmapFactory.Options getHeapOpts(File file) {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			// 数字越大读出的图片占用的heap必须越小，不然总是溢出
			if (file.length() < 20480) { // 0-20k
				opts.inSampleSize = 4;// 这里意为缩放的大小 ，数字越多缩放得越厉害
			} else if (file.length() < 51200) { // 20-50k
				opts.inSampleSize = 6;
			} else if (file.length() < 307200) { // 50-300k
				opts.inSampleSize = 8;
			} else if (file.length() < 819200) { // 300-800k
				opts.inSampleSize = 10;
			} else if (file.length() < 1048576) { // 800-1024k
				opts.inSampleSize = 12;
			} else {
				opts.inSampleSize = 14;
			}
			
			return opts;
		}

	//按比例压缩
		 public static String compressBitamp(Bitmap bitmap, String compressPath, int quality) {
			    FileOutputStream stream = null;
			    try {
			        stream = new FileOutputStream(new File(compressPath));
			        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);// (0-100)压缩文件
			        return compressPath;
			    } catch (Exception e){
			        e.printStackTrace();
			    } finally {
			        try {
			            stream.close();
						if (bitmap != null)
						bitmap.recycle();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }

			    return compressPath;
			}
			    public static Bitmap imageZoom(Bitmap bitMap) {
	                //图片允许最大空间   单位：KB
	                double maxSize =400.00;
	                //将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）  
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	                byte[] b = baos.toByteArray();
	                //将字节换成KB
	                double mid = b.length/1024;
	                //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
	                if (mid > maxSize) {
	                        //获取bitmap大小 是允许最大大小的多少倍
	                        double i = mid / maxSize;
	                        //开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
	                        bitMap = zoomImage(bitMap, bitMap.getWidth() / Math.sqrt(i),
	                                        bitMap.getHeight() / Math.sqrt(i));
	                }
	                return bitMap;
	        }
			    /***
		         * 图片的缩放方法
		         * 
		         * @param bgimage
		         *            ：源图片资源
		         * @param newWidth
		         *            ：缩放后宽度
		         * @param newHeight
		         *            ：缩放后高度
		         * @return
		         */
		        public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                               double newHeight) {
		                // 获取这个图片的宽和高
		                float width = bgimage.getWidth();
		                float height = bgimage.getHeight();
		                // 创建操作图片用的matrix对象
		                Matrix matrix = new Matrix();
		                // 计算宽高缩放率
		                float scaleWidth = ((float) newWidth) / width;
		                float scaleHeight = ((float) newHeight) / height;
		                // 缩放图片动作
		                matrix.postScale(scaleWidth, scaleHeight);
		                Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
		                                (int) height, matrix, true);
		                return bitmap;
		        }

		        public static Bitmap getimage(String srcPath) {
		            BitmapFactory.Options newOpts = new BitmapFactory.Options();
		            //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
		            newOpts.inJustDecodeBounds = true;  
		            Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
		              
		            newOpts.inJustDecodeBounds = false;  
		            int w = newOpts.outWidth;  
		            int h = newOpts.outHeight;  
		            //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为  
		            float hh = 800f;//这里设置高度为800f  
		            float ww = 480f;//这里设置宽度为480f  
		            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
		            int be = 1;//be=1表示不缩放  
		            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
		                be = (int) (newOpts.outWidth / ww);  
		            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
		                be = (int) (newOpts.outHeight / hh);  
		            }  
		            if (be <= 0)  
		                be = 1;  
		            newOpts.inSampleSize = be;//设置缩放比例  
		            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
		            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		            return bitmap;//压缩好比例大小后再进行质量压缩  
		        }

		        public static Bitmap compressImage(Bitmap image) {
		            
		            ByteArrayOutputStream baos = new ByteArrayOutputStream();
		            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		            int options = 100;  
		            while ( baos.toByteArray().length / 1024>300) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
		                baos.reset();//重置baos即清空baos  
		                image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
		                options -= 50;//每次都减少10
		            }  
		            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		            Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
		            return bitmap;  
		        }       
}
