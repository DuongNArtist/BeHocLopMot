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

public class ActivityMathGuess extends Activity implements OnClickListener {

	private static final int MAX = 20;
	private TextView txtAskTitle;
	private TextView txtAskQuestion;
	private Button[] btnAskSubmit;
	private ImageButton btnAskQuit;
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

	private void create() {
		txtAskTitle = (TextView) findViewById(R.id.txtAskTitle);
		txtAskTitle.setText(getResources().getString(R.string.math_guess));
		txtAskQuestion = (TextView) findViewById(R.id.txtAskQuestion);
		btnAskSubmit = new Button[4];
		for (int i = 0; i < btnAskSubmit.length; i++) {
			btnAskSubmit[i] = (Button) findViewById(R.id.btnAskSubmit0 + i);
			btnAskSubmit[i].setOnClickListener(this);
		}
		btnAskQuit = (ImageButton) findViewById(R.id.btnAskQuit);
		btnAskQuit.setOnClickListener(this);
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

	private void submit(int id) {
		if (check(id - R.id.btnAskSubmit0)) {
			playTrue();
		} else {
			playFalse();
		}
	}

	private void playFalse() {
		Dsa.count();
		setEnable(false);
		final MediaPlayer mediaPlayer = MediaPlayer.create(this,
				R.raw.true_false_0);
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						mediaPlayer.release();
						setEnable(true);
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private void playTrue() {
		setEnable(false);
		final MediaPlayer mediaPlayer = MediaPlayer.create(this,
				R.raw.true_false_1 + new Random().nextInt(4));
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						mediaPlayer.release();
						setEnable(true);
						ask();
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private void setEnable(boolean enabled) {
		btnAskQuit.setEnabled(enabled);
		for (Button button : btnAskSubmit) {
			button.setEnabled(enabled);
		}
	}

	private void ask() {
		index = new Random().nextInt(MAX);
		strQuestion = getResources().getStringArray(
				R.array.study_math_guess_00 + index);
		txtAskQuestion.setText(strQuestion[0]);
		for (int i = 0; i < btnAskSubmit.length; i++) {
			btnAskSubmit[i].setText(strQuestion[i + 1]);
		}
	}

	private boolean check(int i) {
		if (strQuestion[strQuestion.length - 1].equals(strQuestion[i + 1])) {
			return true;
		} else {
			return false;
		}
	}
}
