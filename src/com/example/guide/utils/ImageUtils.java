package com.example.guide.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * @author cxm E-mail: 1993baicai@sina.com
 * @date ：2015-8-1 下午11:01:57
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class ImageUtils {

	/**
	 * 
	 * @Title: ListViewBitmap
	 * @Description: TODO(获取照片的bitmap)
	 * @param @param filePath
	 * @param @return 设定文件
	 * @return Bitmap 返回类型
	 * @throws
	 */
	public static Bitmap ListViewBitmap(String filePath) {
		return decodeSampledBitmapFromResource(filePath, 100, 100);
	}

	/**
	 * 
	 * @Title: decodeSampledBitmapFromResource
	 * @Description: TODO(压缩图片)
	 * @param @param srcPath
	 * @param @param reqWidth
	 * @param @param reqHeight
	 * @param @return 设定文件
	 * @return Bitmap 返回类型
	 * @throws
	 */
	public static Bitmap decodeSampledBitmapFromResource(String srcPath, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		Options options = getOptions(srcPath);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(srcPath, options);
	}

	/**
	 * 
	 * @Title: getOptions
	 * @Description: TODO(获得照片尺寸，在option中存放)
	 * @param @param srcPath
	 * @param @return 设定文件
	 * @return Options 返回类型
	 * @throws
	 */
	public static Options getOptions(String srcPath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(srcPath, options);
		return options;
	}

	/**
	 * 
	 * @Title: calculateInSampleSize
	 * @Description: TODO(获得需要压缩的尺寸)
	 * @param @param options
	 * @param @param reqWidth 目标尺寸
	 * @param @param reqHeight
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
}
