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

public class ActivityMathAddSub100 extends Activity implements OnClickListener {

	private ImageButton btnAmas100Return;
	private TextView txtAmas100Operator;
	private TextView[] txtAmas100Number;
	private Button[] btnAmas100Submit;
	private int[] number;
	private int[] temp;
	private int pivot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_add_sub_100);
		create();
		ask();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void create() {
		number = new int[6];
		temp = new int[3];
		txtAmas100Operator = (TextView) findViewById(R.id.txtAmas100Operator);
		txtAmas100Number = new TextView[6];
		for (int i = 0; i < txtAmas100Number.length; i++) {
			txtAmas100Number[i] = (TextView) findViewById(R.id.txtAmas100Number0
					+ i);
		}
		btnAmas100Submit = new Button[10];
		for (int i = 0; i < btnAmas100Submit.length; i++) {
			btnAmas100Submit[i] = (Button) findViewById(R.id.btnAmas100Submit0
					+ i);
			btnAmas100Submit[i].setText("" + i);
			btnAmas100Submit[i].setOnClickListener(this);
		}
		btnAmas100Return = (ImageButton) findViewById(R.id.btnAmas100Return);
		btnAmas100Return.setOnClickListener(this);
	}

	private void ask() {
		Random random = new Random();
		temp[0] = random.nextInt(100);
		temp[2] = random.nextInt(100);
		if (temp[2] >= temp[0]) {
			temp[1] = temp[2] - temp[0];
			txtAmas100Operator.setText("+");
		} else {
			temp[1] = temp[0] - temp[2];
			txtAmas100Operator.setText("-");
		}
		for (int i = 0; i < temp.length; i++) {
			number[2 * i] = temp[i] / 10;
			number[2 * i + 1] = temp[i] % 10;
			txtAmas100Number[2 * i].setText("" + number[2 * i]);
			txtAmas100Number[2 * i + 1].setText("" + number[2 * i + 1]);
		}
		pivot = random.nextInt(6);
		txtAmas100Number[pivot].setText("");
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.btnAmas100Return) {
			finish();
			Dsa.showAd();
		} else {
			submit(id - R.id.btnAmas100Submit0);
		}
	}

	private boolean check(int i) {
		if (i == number[pivot]) {
			return true;
		}
		return false;
	}

	private void submit(int i) {
		if (check(i)) {
			txtAmas100Number[pivot].setText(number[pivot] + "");
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
						mediaPlayer.release();
						setEnable(true);
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
				mediaPlayer.start();
				setEnable(false);
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

	private void setEnable(boolean b) {
		btnAmas100Return.setEnabled(b);
		for (Button button : btnAmas100Submit) {
			button.setEnabled(b);
		}
	}
}
