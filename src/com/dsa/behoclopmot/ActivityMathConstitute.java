package com.dsa.behoclopmot;

import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityMathConstitute extends Activity implements OnClickListener {

	private static final int MAX = 20;
	private TextView txtCsQuestion;
	private Button[] btnCsSubmit;
	private ImageButton btnCsReturn;
	private String[] strQuestion;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_knowledge);
		create();
		ask();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.btnAskQuit) {
			finish();
			Dsa.showAd();
		} else {
			submit(id);
		}
	}

	private void setEnable(boolean enabled) {
		btnCsReturn.setEnabled(enabled);
		for (Button button : btnCsSubmit) {
			button.setEnabled(enabled);
		}
	}

	private void create() {
		index = new Random().nextInt(MAX);
		txtCsQuestion = (TextView) findViewById(R.id.txtAskQuestion);
		btnCsSubmit = new Button[4];
		for (int i = 0; i < btnCsSubmit.length; i++) {
			btnCsSubmit[i] = (Button) findViewById(R.id.btnAskSubmit0 + i);
			btnCsSubmit[i].setOnClickListener(this);
		}
		btnCsReturn = (ImageButton) findViewById(R.id.btnAskQuit);
		btnCsReturn.setOnClickListener(this);
	}

	private void ask() {
		index++;
		if (index > MAX - 1) {
			index = 0;
		}
		strQuestion = getResources().getStringArray(
				R.array.study_math_constitute_00 + index);
		txtCsQuestion.setText(strQuestion[0]);
		for (int i = 0; i < btnCsSubmit.length; i++) {
			btnCsSubmit[i].setText(strQuestion[i + 1]);
		}
	}

	private void submit(int id) {
		if (check(id - R.id.btnAskSubmit0)) {
			playTrue();
		} else {
			playFalse();
		}
	}

	private void playFalse() {
		Dsa.count();
		final MediaPlayer mediaPlayer = MediaPlayer.create(this,
				R.raw.true_false_0);
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				setEnable(false);
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						setEnable(true);
						mediaPlayer.release();
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private void playTrue() {
		final MediaPlayer mediaPlayer = MediaPlayer.create(this,
				R.raw.true_false_1 + new Random().nextInt(4));
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				setEnable(false);
				mediaPlayer.start();
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						setEnable(true);
						mediaPlayer.release();
						ask();
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private boolean check(int i) {
		if (strQuestion[strQuestion.length - 1].equals(strQuestion[i + 1])) {
			return true;
		} else {
			return false;
		}
	}
}
