package com.example.guide.UI;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guide.R;
import com.example.guide.bean.Scenery;
import com.example.guide.utils.FileUtils;
import com.example.guide.utils.ImageUtils;

public class SceneryOneActivity extends Activity {

	private Scenery mScenery;
	private ImageView mPicImageView;
	private TextView mName;
	//private TextView mProvince;
	//private TextView mCity;
	private TextView mLocation;
	private TextView mIntroduce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenery_one);
		Intent intent = getIntent();
		mScenery = (Scenery) intent.getSerializableExtra("scenery");
		mPicImageView = (ImageView) findViewById(R.id.scenery_one_pic);
		mName = (TextView) findViewById(R.id.scenery_one_name_content);
		//mProvince = (TextView) findViewById(R.id.scenery_one_province_content);
		//mCity = (TextView) findViewById(R.id.scenery_one_city_content);
		mLocation = (TextView) findViewById(R.id.scenery_one_location_content);
		mIntroduce= (TextView) findViewById(R.id.scenery_one_introduce_content);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mName.setText(mScenery.getName());
		//mProvince.setText(mScenery.getProvince());
		//mCity.setText(mScenery.getCity());
		mIntroduce.setText(mScenery.getIntroduce());
		mLocation.setText(mScenery.getLocation());
		String picPath = FileUtils.getSDGuidePicPath() + mScenery.getPicUrl() + mScenery.getName() + ".jpg";
		Log.d("cxm", picPath);
		File mFile = new File(picPath);
		if (mFile.exists()) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			Bitmap bitmap = ImageUtils.ListViewBitmap(picPath);
			mPicImageView.setImageBitmap(bitmap);
		}
	}

}
