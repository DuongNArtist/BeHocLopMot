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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityPhilologyCatch extends Activity implements OnClickListener {

	private static ImageView imgApcContent;
	private static ImageButton btnApcQuit;
	private static ImageButton btnApcSpeak;
	public static Handler handler;
	private static String[] strContent;
	private static int index;
	public static TextView[] txtApcDst00;
	private static GridView grdApcSrc;
	private static ArrayList<Integer> integers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_philology_catch);
		create();
		ask();
		speak();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	public static boolean check() {
		for (int i = 0; i < strContent[index].length(); i++) {
			if (txtApcDst00[i].getVisibility() == TextView.INVISIBLE) {
				return false;
			}
		}
		return true;
	}

	public static void ask() {
		if (integers.size() == strContent.length) {
			integers = new ArrayList<Integer>();
		}
		do {
			index = new Random().nextInt(strContent.length);
		} while (integers.contains(index));
		integers.add(index);
		imgApcContent.setImageResource(R.drawable.philology_catch_000 + index);
		grdApcSrc.setAdapter(new AdapterCatcher(Dsa.getContext(), index));
		String strDestination = strContent[index];
		for (int i = 0; i < txtApcDst00.length; i++) {
			if (i < strDestination.length()) {
				txtApcDst00[i].setText(strDestination.substring(i, i + 1));
				if (strDestination.substring(i, i + 1).equals(" ")) {
					txtApcDst00[i].setVisibility(TextView.VISIBLE);
				} else {
					txtApcDst00[i].setVisibility(TextView.INVISIBLE);
				}
			} else {
				txtApcDst00[i].setText("");
				txtApcDst00[i].setVisibility(TextView.GONE);
			}
		}
	}

	public static void speak() {
		final MediaPlayer mediaPlayer = MediaPlayer.create(Dsa.getContext(),
				R.raw.philology_catch_000 + index);
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				setEnable(false);
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

	public static void setEnable(boolean enabled) {
		btnApcQuit.setEnabled(enabled);
		btnApcSpeak.setEnabled(enabled);
	}

	private void create() {
		integers = new ArrayList<Integer>();
		handler = new Handler();
		strContent = getResources().getStringArray(
				R.array.study_philology_catch);
		imgApcContent = (ImageView) findViewById(R.id.imgApcContent);
		btnApcQuit = (ImageButton) findViewById(R.id.btnApcQuit);
		btnApcSpeak = (ImageButton) findViewById(R.id.btnApcSpeak);
		btnApcQuit.setOnClickListener(this);
		btnApcSpeak.setOnClickListener(this);
		grdApcSrc = (GridView) findViewById(R.id.grdApcSrc);
		txtApcDst00 = new TextView[12];
		for (int i = 0; i < txtApcDst00.length; i++) {
			txtApcDst00[i] = (TextView) findViewById(R.id.txtApcDst00 + i);
		}
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnApcSpeak:
			speak();
			break;
		case R.id.btnApcQuit:
			finish();
			Dsa.showAd();
			break;
		default:
			break;
		}
	}
}
