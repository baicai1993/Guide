package com.example.guide.UI;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.guide.R;
import com.example.guide.DB.SceneryDB;
import com.example.guide.adapter.SceneryListAdapter;
import com.example.guide.bean.Scenery;

public class SceneryListActivity extends Activity implements OnItemClickListener {

	private static final String LOG_TAG = "SceneryListActivity";
	private ListView mSceneryListView;
	private SceneryListAdapter mSceneryListAdapter;
	private String mNowProvince;
	private String mNowCity;
	private ArrayList<Scenery> mSceneryList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenery_list);
		mSceneryListView = (ListView) findViewById(R.id.scenery_list_lv);
		mSceneryListView.setOnItemClickListener(this);
		Intent intent = getIntent();
		mNowProvince = intent.getStringExtra("nowProvince");
		mNowCity = intent.getStringExtra("nowCity");
		Log.d(LOG_TAG, "nowProvince = " + mNowProvince + " nowCity = " + mNowCity);
		initListView();
	}

	private void initListView() {
		mSceneryList = (ArrayList<Scenery>) new SceneryDB(SceneryListActivity.this).getCitySceneryInDB(mNowProvince, mNowCity);
		if (mSceneryListAdapter == null) {
			mSceneryListAdapter = new SceneryListAdapter(SceneryListActivity.this, mSceneryList);
			mSceneryListView.setAdapter(mSceneryListAdapter);
		} else {
			mSceneryListAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Scenery scenery = mSceneryList.get(position);
		Intent intent = new Intent(SceneryListActivity.this, SceneryOneActivity.class);
		intent.putExtra("scenery", scenery);
		startActivity(intent);
	}
}
