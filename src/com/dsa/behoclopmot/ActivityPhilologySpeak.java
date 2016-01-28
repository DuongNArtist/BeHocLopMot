package com.dsa.behoclopmot;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityPhilologySpeak extends Activity implements OnClickListener {

	private ImageView imgContent;
	private TextView txtContent;
	private TextView txtTitle;
	private ImageButton btnQuit;
	private ImageButton btnBack;
	private ImageButton btnSpeak;
	private ImageButton btnNext;
	private Handler handler;
	private String[] strContent;
	private int index;
	private int startGfx;
	private int startMfx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_philology_speak);
		handler = new Handler();
		createButton();
		createImage();
		createText();
		createLesson();
		update();
		speak();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void createLesson() {
		strContent = getResources().getStringArray(
				R.array.study_philology_speak_00 + AdapterLesson.indexOfLesson);
		index = 0;
		switch (AdapterLesson.indexOfLesson) {
		case 0:
			startGfx = R.drawable.philology_speak_00_0;
			startMfx = R.raw.philology_speak_00_0;
			break;
		case 1:
			startGfx = R.drawable.philology_speak_01_0;
			startMfx = R.raw.philology_speak_01_0;
			break;
		case 2:
			startGfx = R.drawable.philology_speak_02_0;
			startMfx = R.raw.philology_speak_02_0;
			break;
		case 3:
			startGfx = R.drawable.philology_speak_03_0;
			startMfx = R.raw.philology_speak_03_0;
			break;
		case 4:
			startGfx = R.drawable.philology_speak_04_0;
			startMfx = R.raw.philology_speak_04_0;
			break;
		case 5:
			startGfx = R.drawable.philology_speak_05_0;
			startMfx = R.raw.philology_speak_05_0;
			break;
		case 6:
			startGfx = R.drawable.philology_speak_06_0;
			startMfx = R.raw.philology_speak_06_0;
			break;
		case 7:
			startGfx = R.drawable.philology_speak_07_0;
			startMfx = R.raw.philology_speak_07_0;
			break;
		case 8:
			startGfx = R.drawable.philology_speak_08_0;
			startMfx = R.raw.philology_speak_08_0;
			break;
		case 9:
			startGfx = R.drawable.philology_speak_09_0;
			startMfx = R.raw.philology_speak_09_0;
			break;
		case 10:
			startGfx = R.drawable.philology_speak_10_0;
			startMfx = R.raw.philology_speak_10_0;
			break;
		case 11:
			startGfx = R.drawable.philology_speak_11_0;
			startMfx = R.raw.philology_speak_11_0;
			break;
		case 12:
			startGfx = R.drawable.philology_speak_12_0;
			startMfx = R.raw.philology_speak_12_0;
			break;
		case 13:
			startGfx = R.drawable.philology_speak_13_0;
			startMfx = R.raw.philology_speak_13_0;
			break;
		case 14:
			startGfx = R.drawable.philology_speak_14_0;
			startMfx = R.raw.philology_speak_14_0;
			break;
		case 15:
			startGfx = R.drawable.philology_speak_15_0;
			startMfx = R.raw.philology_speak_15_0;
			break;
		case 16:
			startGfx = R.drawable.philology_speak_16_0;
			startMfx = R.raw.philology_speak_16_0;
			break;
		case 17:
			startGfx = R.drawable.philology_speak_17_0;
			startMfx = R.raw.philology_speak_17_0;
			break;
		case 18:
			startGfx = R.drawable.philology_speak_18_0;
			startMfx = R.raw.philology_speak_18_0;
			break;
		case 19:
			startGfx = R.drawable.philology_speak_19_0;
			startMfx = R.raw.philology_speak_19_0;
			break;
		case 20:
			startGfx = R.drawable.philology_speak_20_0;
			startMfx = R.raw.philology_speak_20_0;
			break;
		case 21:
			startGfx = R.drawable.philology_speak_21_0;
			startMfx = R.raw.philology_speak_21_0;
			break;
		case 22:
			startGfx = R.drawable.philology_speak_22_0;
			startMfx = R.raw.philology_speak_22_0;
			break;
		case 23:
			startGfx = R.drawable.philology_speak_23_0;
			startMfx = R.raw.philology_speak_23_0;
			break;
		case 24:
			startGfx = R.drawable.philology_speak_24_0;
			startMfx = R.raw.philology_speak_24_0;
			break;
		case 25:
			startGfx = R.drawable.philology_speak_25_0;
			startMfx = R.raw.philology_speak_25_0;
			break;
		case 26:
			startGfx = R.drawable.philology_speak_26_0;
			startMfx = R.raw.philology_speak_26_0;
			break;
		case 27:
			startGfx = R.drawable.philology_speak_27_0;
			startMfx = R.raw.philology_speak_27_0;
			break;
		case 28:
			startGfx = R.drawable.philology_speak_28_0;
			startMfx = R.raw.philology_speak_28_0;
			break;
		case 29:
			startGfx = R.drawable.philology_speak_29_0;
			startMfx = R.raw.philology_speak_29_0;
			break;
		case 30:
			startGfx = R.drawable.philology_speak_30_0;
			startMfx = R.raw.philology_speak_30_0;
			break;
		case 31:
			startGfx = R.drawable.philology_speak_31_0;
			startMfx = R.raw.philology_speak_31_0;
			break;
		case 32:
			startGfx = R.drawable.philology_speak_32_0;
			startMfx = R.raw.philology_speak_32_0;
			break;
		case 33:
			startGfx = R.drawable.philology_speak_33_0;
			startMfx = R.raw.philology_speak_33_0;
			break;
		default:
			break;
		}
	}

	private void update() {
		imgContent.setImageResource(startGfx + index);
		txtContent.setText(strContent[index]);
	}

	private void speak() {
		setEnable(false);
		final MediaPlayer mediaPlayer = MediaPlayer.create(this, startMfx
				+ index);
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						setEnable(true);
						mediaPlayer.release();
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private void setEnable(boolean enabled) {
		btnQuit.setEnabled(enabled);
		btnNext.setEnabled(enabled);
		btnQuit.setEnabled(enabled);
		btnSpeak.setEnabled(enabled);
	}

	private void createText() {
		txtTitle = (TextView) findViewById(R.id.txtPhilologySpeakTitle);
		txtContent = (TextView) findViewById(R.id.txtPhilologySpeakContent);
		txtTitle.setText(AdapterLesson.strLessonName[AdapterLesson.indexOfLesson]);
	}

	private void createImage() {
		imgContent = (ImageView) findViewById(R.id.imgPhilologySpeakContent);
	}

	private void createButton() {
		btnQuit = (ImageButton) findViewById(R.id.btnPhilologySpeakReturn);
		btnQuit.setOnClickListener(this);
		btnSpeak = (ImageButton) findViewById(R.id.btnPhilologySpeakSpeak);
		btnSpeak.setOnClickListener(this);
		btnBack = (ImageButton) findViewById(R.id.btnPhilologySpeakBack);
		btnBack.setOnClickListener(this);
		btnNext = (ImageButton) findViewById(R.id.btnPhilologySpeakNext);
		btnNext.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnPhilologySpeakBack:
			back();
			break;
		case R.id.btnPhilologySpeakSpeak:
			speak();
			break;
		case R.id.btnPhilologySpeakNext:
			next();
			break;
		case R.id.btnPhilologySpeakReturn:
			quit();
			break;
		default:
			break;
		}
	}

	private void back() {
		index--;
		if (index < 0) {
			index = strContent.length - 1;
		}
		update();
		speak();
	}

	private void next() {
		index++;
		if (index >= strContent.length) {
			index = 0;
		}
		update();
		speak();
	}

	private void quit() {
		finish();
		Dsa.showAd();
	}
}
