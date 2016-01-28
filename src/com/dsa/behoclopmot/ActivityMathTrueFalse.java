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

public class ActivityMathTrueFalse extends Activity implements OnClickListener {

	public static final int MAX = 30;
	private ImageButton btnAmtfReturn;
	private TextView txtAmtfQuestion;
	private Button[] btnAmtfSubmit;
	private String[] question;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_true_false);
		create();
		ask();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void ask() {
		index = new Random().nextInt(MAX);
		question = getResources().getStringArray(
				R.array.study_math_true_false_00 + index);
		txtAmtfQuestion.setText(question[0]);
	}

	private void create() {
		txtAmtfQuestion = (TextView) findViewById(R.id.txtAmtfQuestion);
		btnAmtfSubmit = new Button[2];
		for (int i = 0; i < btnAmtfSubmit.length; i++) {
			btnAmtfSubmit[i] = (Button) findViewById(R.id.btnAmtfSubmit0 + i);
			btnAmtfSubmit[i].setOnClickListener(this);
		}
		btnAmtfReturn = (ImageButton) findViewById(R.id.btnAmtfReturn);
		btnAmtfReturn.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.btnAmtfReturn) {
			finish();
			Dsa.showAd();
		} else {
			submit(id - R.id.btnAmtfSubmit0);
		}
	}

	private void submit(int i) {
		if (check(i)) {
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

	private boolean check(int i) {
		if (question[1].equals(i + "")) {
			return true;
		}
		return false;
	}

	private void setEnable(boolean b) {
		btnAmtfReturn.setEnabled(b);
		for (Button button : btnAmtfSubmit) {
			button.setEnabled(b);
		}
	}
}
