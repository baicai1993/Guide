package com.example.guide.UI;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.guide.R;
import com.example.guide.utils.FileUtils;
import com.example.guide.utils.ImageUtils;

public class MainActivity extends Activity implements OnClickListener {
	private static final String LOG_TAG = "MainActivity";

	private Spinner mProvinceSpinner;
	private Spinner mCitySpinner;
	private Integer mProvinceId;
	private Button mSureBtn;
	private ImageView mProvinceImageView;
	private String mNowProvince, mNowCity;

	private int[] mCities = { R.array.beijin_province_item, R.array.tianjin_province_item, R.array.heibei_province_item, R.array.shanxi1_province_item, R.array.neimenggu_province_item, R.array.liaoning_province_item, R.array.jilin_province_item, R.array.heilongjiang_province_item, R.array.shanghai_province_item, R.array.jiangsu_province_item, R.array.zhejiang_province_item, R.array.anhui_province_item, R.array.fujian_province_item, R.array.jiangxi_province_item, R.array.shandong_province_item, R.array.henan_province_item, R.array.hubei_province_item, R.array.hunan_province_item, R.array.guangdong_province_item, R.array.guangxi_province_item, R.array.hainan_province_item, R.array.chongqing_province_item, R.array.sichuan_province_item, R.array.guizhou_province_item,
			R.array.yunnan_province_item, R.array.xizang_province_item, R.array.shanxi2_province_item, R.array.gansu_province_item, R.array.qinghai_province_item, R.array.linxia_province_item, R.array.xinjiang_province_item, R.array.hongkong_province_item, R.array.aomen_province_item, R.array.taiwan_province_item };

	private ArrayAdapter<CharSequence> mProvinceAdapter;
	private ArrayAdapter<CharSequence> mCityAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mProvinceSpinner = (Spinner) findViewById(R.id.main_province_sp);
		mCitySpinner = (Spinner) findViewById(R.id.main_city_sp);
		mSureBtn = (Button) findViewById(R.id.main_sure_btn);
		mProvinceImageView = (ImageView) findViewById(R.id.main_province_pic);
		mSureBtn.setOnClickListener(this);

		loadSpinner();

	}

	@Override
	protected void onResume() {
		super.onResume();
		loadImageView();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.main_sure_btn:
			Log.d(LOG_TAG, "nowProvince = " + mNowProvince + " nowCity = " + mNowCity);
			Intent intent = new Intent(MainActivity.this, SceneryListActivity.class);
			intent.putExtra("nowProvince", mNowProvince);
			intent.putExtra("nowCity", mNowCity);
			startActivity(intent);

			break;
		default:
			break;
		}
	}

	private void loadSpinner() {

		mProvinceSpinner.setPrompt("请选择省份");
		mCitySpinner.setPrompt("请选择市区");
		mProvinceAdapter = ArrayAdapter.createFromResource(this, R.array.province_item, android.R.layout.simple_spinner_item);
		mProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mProvinceSpinner.setAdapter(mProvinceAdapter);

		mProvinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				loadImageView();
				mProvinceId = mProvinceSpinner.getSelectedItemPosition();
				mNowProvince = mProvinceSpinner.getSelectedItem().toString();
				mCityAdapter = ArrayAdapter.createFromResource(MainActivity.this, mCities[mProvinceId], android.R.layout.simple_spinner_item);
				mCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mCitySpinner.setAdapter(mCityAdapter);
				mCitySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						mNowCity = mCitySpinner.getSelectedItem().toString();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

	}

	private void loadImageView() {
		String picPath = FileUtils.getSDGuideProvincePicPath() + mProvinceSpinner.getSelectedItem() + ".jpg";
		Log.d("cxm", picPath);
		File mFile = new File(picPath);
		if (mFile.exists()) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			Bitmap bitmap = ImageUtils.ListViewBitmap(picPath);
			mProvinceImageView.setImageBitmap(bitmap);
		}
	}
}