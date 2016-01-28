package com.dsa.behoclopmot;

import com.dsa.behoclopmot.utilities.Dsa;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ActivityMain extends Activity implements OnClickListener {

	private ImageButton btnMainApp;
	private ImageButton btnMainQuit;
	private ImageButton btnMainRate;
	private ImageButton btnMainStudy;
	private ImageButton btnMainGame;
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		create();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Dsa.createAd(this);
		mediaPlayer = MediaPlayer.create(this, R.raw.music);
		mediaPlayer.setLooping(true);
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		mediaPlayer.stop();
		mediaPlayer.release();
	}

	private void create() {
		btnMainApp = (ImageButton) findViewById(R.id.btnMainApp);
		btnMainApp.setOnClickListener(this);
		btnMainGame = (ImageButton) findViewById(R.id.btnMainGame);
		btnMainGame.setOnClickListener(this);
		btnMainStudy = (ImageButton) findViewById(R.id.btnMainStudy);
		btnMainStudy.setOnClickListener(this);
		btnMainQuit = (ImageButton) findViewById(R.id.btnMainQuit);
		btnMainQuit.setOnClickListener(this);
		btnMainRate = (ImageButton) findViewById(R.id.btnMainRate);
		btnMainRate.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnMainGame:
			startActivity(new Intent(getBaseContext(), ActivityGame.class));
			break;
		case R.id.btnMainStudy:
			startActivity(new Intent(getBaseContext(), ActivityStudy.class));
			break;
		case R.id.btnMainQuit:
			finish();
			Dsa.showAd();
			break;
		case R.id.btnMainRate:
			goToRate();
			break;
		case R.id.btnMainApp:
			goToApp();
			break;
		default:
			break;
		}
	}

	private void goToApp() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri
				.parse("https://play.google.com/store/apps/developer?id=DSA+Inc"));
		startActivity(intent);
	}

	private void goToRate() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id="
				+ getApplicationContext().getPackageName()));
		startActivity(intent);
	}
}
