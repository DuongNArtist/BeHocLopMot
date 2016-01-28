package com.dsa.behoclopmot;

import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;
import com.dsa.behoclopmot.utilities.Gfx;

public class ActivityMathCompare extends Activity implements OnClickListener {

	private static final int[] idPool = { R.id.lnrCmpPool0, R.id.lnrCmpPool1 };
	private static final int[] idNumber = { R.id.txtCmpNumber0,
			R.id.txtCmpNumber1 };
	private static final String[] strCmp = { ">", "=", "<" };
	private ImageButton btnCmpReturn;
	private LinearLayout[] lnrCmpPool;
	private TextView[] txtCmpNumber;
	private TextView txtCmpCompare;
	private Button[] btnCmpNumber;
	private Button[] btnCmpCompare;
	private int[] number;
	private int key;
	private int pivot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_compare);
		number = new int[2];
		create();
		ask();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void ask() {
		Random random = new Random();
		for (int i = 0; i < number.length; i++) {
			number[i] = random.nextInt(5);
		}
		int result = number[0] - number[1];
		if (result > 0) {
			key = 0;
		} else if (result < 0) {
			key = 2;
		} else {
			key = 1;
		}

		txtCmpCompare.setText("");

		for (int i = 0; i < number.length; i++) {
			lnrCmpPool[i].removeAllViews();
			for (int j = 0; j < number[i]; j++) {
				ImageView imageView = new ImageView(this);
				// imageView.setImageResource(R.drawable.ic_launcher);
				imageView.setImageBitmap(Gfx.getPoke());
				imageView.setScaleType(ScaleType.FIT_XY);
				int size = (int) (50 * Gfx.srcDensity);
				imageView.setLayoutParams(new LayoutParams(size, size));
				lnrCmpPool[i].addView(imageView);
			}
		}

		pivot = random.nextInt(2);
		txtCmpNumber[pivot].setText("");
		txtCmpNumber[1 - pivot].setText(number[1 - pivot] + "");
	}

	private void create() {
		lnrCmpPool = new LinearLayout[2];
		for (int i = 0; i < lnrCmpPool.length; i++) {
			lnrCmpPool[i] = (LinearLayout) findViewById(idPool[i]);
		}
		btnCmpReturn = (ImageButton) findViewById(R.id.btnCmpReturn);
		btnCmpReturn.setOnClickListener(this);
		btnCmpNumber = new Button[5];
		for (int i = 0; i < btnCmpNumber.length; i++) {
			btnCmpNumber[i] = (Button) findViewById(R.id.btnCmpNumber0 + i);
			btnCmpNumber[i].setOnClickListener(this);
			btnCmpNumber[i].setText(i + "");
		}
		btnCmpCompare = new Button[3];
		for (int i = 0; i < btnCmpCompare.length; i++) {
			btnCmpCompare[i] = (Button) findViewById(R.id.btnCmpCompare0 + i);
			btnCmpCompare[i].setOnClickListener(this);
			btnCmpCompare[i].setText(strCmp[i]);
		}
		txtCmpCompare = (TextView) findViewById(R.id.txtCmpCompare);
		txtCmpNumber = new TextView[2];
		for (int i = 0; i < txtCmpNumber.length; i++) {
			txtCmpNumber[i] = (TextView) findViewById(idNumber[i]);
		}
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.btnCmpReturn) {
			finish();
			Dsa.showAd();
		} else {
			if (id <= R.id.btnCmpCompare2 && id >= R.id.btnCmpCompare0) {
				if (submitCompare(id - R.id.btnCmpCompare0)) {
					txtCmpCompare.setText(strCmp[key]);
				} else {
					playFalse();
				}
			}
			if (id <= R.id.btnCmpNumber4 && id >= R.id.btnCmpNumber0) {
				if (submitNumber(id - R.id.btnCmpNumber0)) {
					txtCmpNumber[pivot].setText(number[pivot] + "");
				} else {
					playFalse();
				}
			}
			if (txtCmpCompare.getText().length() != 0
					&& txtCmpNumber[0].getText().length() != 0
					&& txtCmpNumber[1].getText().length() != 0) {
				playTrue();
			}
		}
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

	private boolean submitNumber(int i) {
		boolean b = true;
		if (number[pivot] != i) {
			b = false;
		}
		return b;
	}

	private boolean submitCompare(int i) {
		boolean b = true;
		if (i != key) {
			b = false;
		}
		return b;
	}

	private void setEnable(boolean b) {
		btnCmpReturn.setEnabled(b);
		for (int i = 0; i < btnCmpCompare.length; i++) {
			btnCmpCompare[i].setEnabled(b);
			btnCmpNumber[i].setEnabled(b);
		}
	}
}
