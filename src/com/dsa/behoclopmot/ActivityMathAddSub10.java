package com.dsa.behoclopmot;

import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityMathAddSub10 extends Activity implements OnClickListener {

	private static final int[] idTxt = { R.id.txtAs10Number0,
			R.id.txtAs10Number1, R.id.txtAs10Number2, R.id.txtAs10Number3,
			R.id.txtAs10Number4 };
	private static final int[] idImg = { R.id.imgAs10Up0, R.id.imgAs10Up1,
			R.id.imgAs10Up2, R.id.imgAs10Up3, R.id.imgAs10Up4 };
	private ImageButton btnAs10Return;
	private TextView[] txtAs10Number;
	private ImageView[] imgAs10Up;
	private Button[] btnAs10Submit;
	private int[] number;
	private int pivot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_add_sub_10);
		create();
		ask();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void create() {
		number = new int[3];
		imgAs10Up = new ImageView[5];
		for (int i = 0; i < imgAs10Up.length; i++) {
			imgAs10Up[i] = (ImageView) findViewById(idImg[i]);
			((AnimationDrawable) imgAs10Up[i].getDrawable()).start();
		}
		txtAs10Number = new TextView[5];
		for (int i = 0; i < txtAs10Number.length; i++) {
			txtAs10Number[i] = (TextView) findViewById(idTxt[i]);
		}
		txtAs10Number[3].setText("=");
		btnAs10Submit = new Button[10];
		for (int i = 0; i < btnAs10Submit.length; i++) {
			btnAs10Submit[i] = (Button) findViewById(R.id.btnAs10Submit0 + i);
			btnAs10Submit[i].setText("" + i);
			btnAs10Submit[i].setOnClickListener(this);
		}
		btnAs10Return = (ImageButton) findViewById(R.id.btnAs10Return);
		btnAs10Return.setOnClickListener(this);
	}

	private void ask() {
		Random random = new Random();
		number[0] = random.nextInt(10);
		txtAs10Number[0].setText(number[0] + "");
		number[2] = random.nextInt(10);
		txtAs10Number[4].setText(number[2] + "");

		if (number[2] >= number[0]) {
			number[1] = number[2] - number[0];
			txtAs10Number[1].setText("+");
		} else {
			number[1] = number[0] - number[2];
			txtAs10Number[1].setText("-");
		}

		txtAs10Number[2].setText(number[1] + "");

		for (int i = 0; i < imgAs10Up.length; i++) {
			imgAs10Up[i].setVisibility(ImageView.INVISIBLE);
		}

		pivot = random.nextInt(3);
		switch (pivot) {
		case 0:
			txtAs10Number[0].setText("");
			imgAs10Up[0].setVisibility(ImageView.VISIBLE);
			break;
		case 1:
			txtAs10Number[2].setText("");
			imgAs10Up[2].setVisibility(ImageView.VISIBLE);
			break;
		case 2:
			txtAs10Number[4].setText("");
			imgAs10Up[4].setVisibility(ImageView.VISIBLE);
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.btnAs10Return) {
			finish();
			Dsa.showAd();
		} else {
			submit(id - R.id.btnAs10Submit0);
		}
	}

	private void submit(int i) {
		if (check(i)) {
			txtAs10Number[pivot * 2].setText(number[pivot] + "");
			playTrue();
		} else {
			playFalse();
		}
	}

	private boolean check(int i) {
		if (i == number[pivot]) {
			return true;
		}
		return false;
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

	private void setEnable(boolean b) {
		btnAs10Return.setEnabled(b);
		for (Button button : btnAs10Submit) {
			button.setEnabled(b);
		}
	}
}
