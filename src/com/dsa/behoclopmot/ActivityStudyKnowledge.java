package com.dsa.behoclopmot;

import java.util.ArrayList;
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

public class ActivityStudyKnowledge extends Activity implements OnClickListener {

	public static final int MAX = 120;
	private static final int START = R.array.study_knowledge_question_000;
	private TextView txtAskTitle;
	private TextView txtAskQuestion;
	private Button[] btnAskSubmit;
	private ImageButton btnAskQuit;
	private String[] strQuestion;
	private int index;
	private ArrayList<Integer> integers;
	private int score;

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
		score = 0;
		integers = new ArrayList<Integer>();
		txtAskTitle = (TextView) findViewById(R.id.txtAskTitle);
		txtAskTitle.setText(getResources()
				.getString(R.string.knowledge_message) + " " + score);
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
			new ToastDuong(this, score);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					finish();
					Dsa.showAd();
				}
			}, 4000);
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

	private void playTrue() {
		score++;
		txtAskTitle.setText(getResources()
				.getString(R.string.knowledge_message) + " " + score);
		final MediaPlayer mediaPlayer = MediaPlayer.create(this,
				R.raw.true_false_1 + new Random().nextInt(4));
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
						ask();
					}
				}, mediaPlayer.getDuration());
			}
		});
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

	private void ask() {
		if (integers.size() == MAX) {
			integers = new ArrayList<Integer>();
		}
		do {
			index = new Random().nextInt(MAX);
		} while (integers.contains(index));
		integers.add(index);
		strQuestion = getResources().getStringArray(START + index);
		txtAskQuestion.setText(strQuestion[0]);
		strQuestion = shuffle(strQuestion);
		for (int i = 0; i < btnAskSubmit.length; i++) {
			btnAskSubmit[i].setText(strQuestion[i + 1]);
		}
	}

	private String[] shuffle(String[] strings) {
		Random rnd = new Random();
		for (int i = strings.length - 2; i > 0; i--) {
			int index = 1 + rnd.nextInt(strings.length - 2);
			String a = strings[index];
			strings[index] = strings[i];
			strings[i] = a;
		}
		return strings;
	}

	private boolean check(int i) {
		if (strQuestion[strQuestion.length - 1]
				.equalsIgnoreCase(strQuestion[i + 1])) {
			return true;
		}
		return false;
	}

	private void setEnable(boolean enabled) {
		btnAskQuit.setEnabled(enabled);
		for (Button button : btnAskSubmit) {
			button.setEnabled(enabled);
		}
	}
}
