package com.example.guide.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.guide.R;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
				finish();
			}
		}, 2000);

	}

}
