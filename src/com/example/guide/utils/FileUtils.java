package com.example.guide.utils;

import java.io.File;

import android.os.Environment;

/**
 * @author cxm E-mail: 1993baicai@sina.com
 * @date ：2015-8-1 下午3:16:53
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class FileUtils {

	public static String getSDPath() {
		String path = "";
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			path = Environment.getExternalStorageDirectory().toString();
		}
		return path;
	}

	public static String getSDGuidePath() {
		String path = getSDPath() + "/Guide/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	public static String getSDGuidePicPath() {
		String path = getSDGuidePath() + "pic/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	public static String getSDGuideProvincePicPath() {
		String path = getSDGuidePath() + "province_pic/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
}
