package com.dsa.behoclopmot;

import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityMathCalculate extends Activity implements OnClickListener,
		OnDragListener, OnTouchListener {

	private static final int MAX = 20;
	private String[] question;
	private int index;

	private static final int[] idAmc2Key = { R.id.txtAmc2Key0,
			R.id.txtAmc2Key1, R.id.txtAmc2Key2, R.id.txtAmc2Key3 };
	private TextView[] txtAmc2Key;
	private static final int[] idAmc2Value = { R.id.txtAmc2Value0,
			R.id.txtAmc2Value1, R.id.txtAmc2Value2, R.id.txtAmc2Value3 };
	private TextView[] txtAmc2Value;
	private static final int[] idAmc2Pool = { R.id.lnrAmc2Pool0,
			R.id.lnrAmc2Pool1, R.id.lnrAmc2Pool2, R.id.lnrAmc2Pool3 };
	private LinearLayout[] lnrAmc2Pool;

	private ImageButton btnAmc2Quit;
	private LinearLayout lnrAmc2Source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math_calculate);
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
				R.array.study_math_test_00 + index);
		lnrAmc2Source.removeAllViews();
		for (int i = 0; i < txtAmc2Key.length; i++) {
			txtAmc2Key[i].setText(question[i]);
			txtAmc2Value[i].setText(question[i + txtAmc2Key.length]);
			lnrAmc2Pool[i].removeAllViews();
		}
		int[] temp = new int[4];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = i;
		}
		temp = mix(temp);
		for (int i = 0; i < temp.length; i++) {
			lnrAmc2Source.addView(txtAmc2Value[temp[i]]);
		}
	}

	private int[] mix(int[] arr) {
		Random ran = new Random();
		for (int i = 0; i < arr.length; i++) {
			int j = ran.nextInt(arr.length);
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		return arr;
	}

	private void create() {
		index = -1;
		lnrAmc2Source = (LinearLayout) findViewById(R.id.lnrAmc2Source);
		lnrAmc2Source.setOnDragListener(this);
		lnrAmc2Pool = new LinearLayout[4];
		for (int i = 0; i < lnrAmc2Pool.length; i++) {
			lnrAmc2Pool[i] = (LinearLayout) findViewById(idAmc2Pool[i]);
			lnrAmc2Pool[i].setOnDragListener(this);
		}
		txtAmc2Key = new TextView[4];
		txtAmc2Value = new TextView[4];
		for (int i = 0; i < txtAmc2Key.length; i++) {
			txtAmc2Key[i] = (TextView) findViewById(idAmc2Key[i]);
			txtAmc2Value[i] = (TextView) findViewById(idAmc2Value[i]);
			txtAmc2Value[i].setOnTouchListener(this);
		}
		btnAmc2Quit = (ImageButton) findViewById(R.id.btnAmc2Quit);
		btnAmc2Quit.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.btnAmc2Quit) {
			finish();
			Dsa.showAd();
		}
	}

	private boolean check() {
		boolean[] check = new boolean[4];
		for (int i = 0; i < check.length; i++) {
			check[i] = ((ViewGroup) txtAmc2Value[i].getParent()).getId() == idAmc2Pool[i];
			if (!check[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(null, dragShadowBuilder, v, 0);
			return true;
		}
		return false;
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		if (event.getAction() == DragEvent.ACTION_DROP) {
			View view = (View) event.getLocalState();
			int idView = view.getId();
			int idV = v.getId();
			boolean check0 = idView == R.id.txtAmc2Value0
					&& idV == R.id.lnrAmc2Pool0;
			boolean check1 = idView == R.id.txtAmc2Value1
					&& idV == R.id.lnrAmc2Pool1;
			boolean check2 = idView == R.id.txtAmc2Value2
					&& idV == R.id.lnrAmc2Pool2;
			boolean check3 = idView == R.id.txtAmc2Value3
					&& idV == R.id.lnrAmc2Pool3;
			if (check0 || check1 || check2 || check3) {
				ViewGroup viewGroup = (ViewGroup) view.getParent();
				viewGroup.removeView(view);
				LinearLayout linearLayout = (LinearLayout) v;
				linearLayout.addView(view);
			} else {
				playFalse();
			}
			if (check()) {
				playTrue();
			}
		}
		return true;
	}

	private void playTrue() {
		btnAmc2Quit.setEnabled(false);
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
						btnAmc2Quit.setEnabled(true);
						ask();
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private void playFalse() {
		Dsa.count();
		btnAmc2Quit.setEnabled(false);
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
						btnAmc2Quit.setEnabled(true);
					}
				}, mediaPlayer.getDuration());
			}
		});
	}
}
