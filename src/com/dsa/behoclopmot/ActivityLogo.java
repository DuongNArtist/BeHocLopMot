package com.dsa.behoclopmot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActivityLogo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity(new Intent(getBaseContext(), ActivityMain.class));
				finish();
			}
		}, 1500);
	}

	@Override
	public void onBackPressed() {
		return;
	}
}
