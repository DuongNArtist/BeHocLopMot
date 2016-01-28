package com.dsa.behoclopmot;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityPhilologyJoin extends Activity implements OnClickListener,
		OnTouchListener, OnDragListener {

	public static final int NUMBER_OF_EXAMPLES = 6;
	public static final int NUMBER_OF_VOICES = 5;

	private ImageView imgPhilologyJoinContent;

	private LinearLayout lnrPhilologyJoinPlay;
	private LinearLayout lnrPhilologyJoinSource;
	private LinearLayout lnrPhilologyJoinPass;
	private LinearLayout lnrPhilologyJoinDestination0;
	private LinearLayout lnrPhilologyJoinDestination1;
	private LinearLayout lnrPhilologyJoinDestination2;
	private LinearLayout lnrPhilologyJoinDestination3;

	private TextView txtPhilologyJoinTitle;
	private TextView txtPhilologyJoinSource0;
	private TextView txtPhilologyJoinSource1;
	private TextView txtPhilologyJoinSource2;

	private TextView txtPhilologyJoinDestination0;
	private TextView txtPhilologyJoinDestination1;

	private ImageButton btnPhilologyJoinReturn;
	private ImageButton btnPhilologyJoinReplay;
	private ImageButton btnPhilologyJoinSpeak;

	private int index;
	private int startMfx;
	private int startGfx;
	private String[] strContent;

	private Context context;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_philology_join);
		handler = new Handler();
		context = this;
		index = 0;
		startGfx = R.drawable.philology_join_00_0 + AdapterLesson.indexOfLesson
				* NUMBER_OF_EXAMPLES;
		createButton();
		createImage();
		createText();
		createLayout();
		updateContent();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void updateContent() {
		if (index < NUMBER_OF_EXAMPLES) {
			lnrPhilologyJoinPlay.setVisibility(LinearLayout.VISIBLE);
		} else {
			lnrPhilologyJoinPlay.setVisibility(LinearLayout.INVISIBLE);
		}
		strContent = getResources().getStringArray(
				R.array.study_philology_join_000 + AdapterLesson.indexOfLesson
						* NUMBER_OF_EXAMPLES + index);
		startMfx = R.raw.philology_join_000_0 + AdapterLesson.indexOfLesson
				* NUMBER_OF_EXAMPLES * NUMBER_OF_VOICES + index
				* NUMBER_OF_VOICES;
		imgPhilologyJoinContent.setImageResource(startGfx + index);
		txtPhilologyJoinDestination0.setText("");
		txtPhilologyJoinDestination1.setText("");
		setEnableText();
		txtPhilologyJoinSource0.setText(strContent[0]);
		txtPhilologyJoinSource1.setText(strContent[1]);
		txtPhilologyJoinSource2.setText(convertTextToSymbol(strContent[3]));
		if (strContent[2].equals("")) {
			txtPhilologyJoinSource2.setVisibility(TextView.INVISIBLE);
			lnrPhilologyJoinDestination2.setVisibility(LinearLayout.INVISIBLE);
			lnrPhilologyJoinDestination3.setVisibility(LinearLayout.INVISIBLE);
		} else {
			txtPhilologyJoinSource2.setVisibility(TextView.VISIBLE);
			if (strContent[3].equals("5")) {
				lnrPhilologyJoinDestination2
						.setVisibility(LinearLayout.INVISIBLE);
				lnrPhilologyJoinDestination3
						.setVisibility(LinearLayout.VISIBLE);
			} else {
				lnrPhilologyJoinDestination2
						.setVisibility(LinearLayout.VISIBLE);
				lnrPhilologyJoinDestination3
						.setVisibility(LinearLayout.INVISIBLE);
			}
		}
		if (strContent[1].equals("")) {
			txtPhilologyJoinSource1.setVisibility(TextView.INVISIBLE);
			lnrPhilologyJoinDestination0.setVisibility(LinearLayout.INVISIBLE);
		} else {
			txtPhilologyJoinSource1.setVisibility(TextView.VISIBLE);
			lnrPhilologyJoinDestination0.setVisibility(LinearLayout.VISIBLE);
		}
	}

	private String convertTextToSymbol(String string) {
		String result = "";
		switch (string) {
		case "1":
			result = "`";
			break;
		case "2":
			result = "'";
			break;
		case "3":
			result = "?";
			break;
		case "4":
			result = "~";
			break;
		case "5":
			result = ".";
			break;
		default:
			break;
		}
		return result;
	}

	private void createLayout() {
		lnrPhilologyJoinPlay = (LinearLayout) findViewById(R.id.lnrPhilologyJoinPlay);
		lnrPhilologyJoinSource = (LinearLayout) findViewById(R.id.lnrPhilologyJoinSource);
		lnrPhilologyJoinSource.setOnDragListener(this);
		lnrPhilologyJoinPass = (LinearLayout) findViewById(R.id.lnrPhilologyJoinPass);
		lnrPhilologyJoinPass.setVisibility(LinearLayout.INVISIBLE);
		lnrPhilologyJoinDestination0 = (LinearLayout) findViewById(R.id.lnrPhilologyJoinDestination0);
		lnrPhilologyJoinDestination1 = (LinearLayout) findViewById(R.id.lnrPhilologyJoinDestination1);
		lnrPhilologyJoinDestination2 = (LinearLayout) findViewById(R.id.lnrPhilologyJoinDestination2);
		lnrPhilologyJoinDestination3 = (LinearLayout) findViewById(R.id.lnrPhilologyJoinDestination3);
		lnrPhilologyJoinDestination0.setOnDragListener(this);
		lnrPhilologyJoinDestination1.setOnDragListener(this);
		lnrPhilologyJoinDestination2.setOnDragListener(this);
		lnrPhilologyJoinDestination3.setOnDragListener(this);
	}

	private void setEnableButton(boolean enabled) {
		imgPhilologyJoinContent.setEnabled(enabled);
		btnPhilologyJoinSpeak.setEnabled(enabled);
		btnPhilologyJoinReturn.setEnabled(enabled);
	}

	private void createText() {
		txtPhilologyJoinTitle = (TextView) findViewById(R.id.txtPhilologyJoinTitle);
		txtPhilologyJoinSource0 = (TextView) findViewById(R.id.txtPhilologyJoinSource0);
		txtPhilologyJoinSource1 = (TextView) findViewById(R.id.txtPhilologyJoinSource1);
		txtPhilologyJoinSource2 = (TextView) findViewById(R.id.txtPhilologyJoinSource2);

		txtPhilologyJoinSource0.setOnTouchListener(this);
		txtPhilologyJoinSource1.setOnTouchListener(this);
		txtPhilologyJoinSource2.setOnTouchListener(this);

		txtPhilologyJoinDestination0 = (TextView) findViewById(R.id.txtPhilologyJoinDestination0);
		txtPhilologyJoinDestination1 = (TextView) findViewById(R.id.txtPhilologyJoinDestination1);

		txtPhilologyJoinTitle
				.setText(AdapterLesson.strLessonName[AdapterLesson.indexOfLesson]);
	}

	private void createImage() {
		imgPhilologyJoinContent = (ImageView) findViewById(R.id.imgPhilologyJoinContent);
		imgPhilologyJoinContent.setOnClickListener(this);
	}

	private void createButton() {
		btnPhilologyJoinReturn = (ImageButton) findViewById(R.id.btnPhilologyJoinReturn);
		btnPhilologyJoinReplay = (ImageButton) findViewById(R.id.btnPhilologyJoinReplay);
		btnPhilologyJoinSpeak = (ImageButton) findViewById(R.id.btnPhilologyJoinSpeak);
		btnPhilologyJoinReplay.setOnClickListener(this);
		btnPhilologyJoinReturn.setOnClickListener(this);
		btnPhilologyJoinSpeak.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.imgPhilologyJoinContent:
			onClickPhilologyJoinSpeak();
			break;
		case R.id.btnPhilologyJoinSpeak:
			onClickPhilologyJoinSpeak();
			break;
		case R.id.btnPhilologyJoinReturn:
			finish();
			Dsa.showAd();
			break;
		case R.id.btnPhilologyJoinReplay:
			lnrPhilologyJoinPass.setVisibility(LinearLayout.INVISIBLE);
			break;
		default:
			break;
		}
	}

	private void onClickPhilologyJoinSpeak() {
		final MediaPlayer mediaPlayer = MediaPlayer.create(this, startMfx + 4);
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				setEnableButton(false);
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						setEnableButton(true);
						mediaPlayer.release();
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private void nextContent() {
		index++;
		if (index == NUMBER_OF_EXAMPLES) {
			Dsa.setPassed(AdapterLesson.typeOfLesson,
					AdapterLesson.indexOfLesson, true);
			lnrPhilologyJoinPass.setVisibility(LinearLayout.VISIBLE);
			index = 0;
		}
	}

	@Override
	public boolean onDrag(View viewDestination, DragEvent dragEvent) {
		if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
			View viewSource = (View) dragEvent.getLocalState();
			int idSource = viewSource.getId();
			int idDestination = viewDestination.getId();
			final boolean check0 = idSource == R.id.txtPhilologyJoinSource0
					&& idDestination == R.id.lnrPhilologyJoinDestination1;
			final boolean check1 = idSource == R.id.txtPhilologyJoinSource1
					&& idDestination == R.id.lnrPhilologyJoinDestination0;
			final boolean check2 = idSource == R.id.txtPhilologyJoinSource2
					&& (idDestination == R.id.lnrPhilologyJoinDestination2 || idDestination == R.id.lnrPhilologyJoinDestination3)
					&& strContent[0].equals("");
			if (check0 || check1 || check2) {
				viewSource.setEnabled(false);
				if (check0) {
					txtPhilologyJoinDestination1.setText(strContent[0]);
					strContent[0] = "";
					final MediaPlayer mediaPlayer = MediaPlayer.create(this,
							startMfx + 1);
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							mediaPlayer.start();
							handler.postDelayed(new Runnable() {

								@Override
								public void run() {
									mediaPlayer.release();
								}
							}, mediaPlayer.getDuration());
						}
					});
				} else if (check1) {
					txtPhilologyJoinDestination0.setText(strContent[1]);
					final MediaPlayer mediaPlayer = MediaPlayer.create(this,
							startMfx + 0);
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							mediaPlayer.start();
							handler.postDelayed(new Runnable() {

								@Override
								public void run() {
									mediaPlayer.release();
								}
							}, mediaPlayer.getDuration());
						}
					});
				} else if (check2) {
					txtPhilologyJoinDestination1.setText(strContent[2]);
					strContent[2] = "";
					lnrPhilologyJoinDestination2
							.setVisibility(LinearLayout.INVISIBLE);
					lnrPhilologyJoinDestination3
							.setVisibility(LinearLayout.INVISIBLE);
					final MediaPlayer mediaPlayer = MediaPlayer.create(this,
							startMfx + 2);
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							mediaPlayer.start();
							handler.postDelayed(new Runnable() {

								@Override
								public void run() {
									mediaPlayer.release();
								}
							}, mediaPlayer.getDuration());
						}
					});
				}
				if (checkAnswer()) {
					// playTrue();
				}
			} else {
				playFalse();
			}
		}
		return true;
	}

	@SuppressWarnings("unused")
	private void playTrue() {
		final MediaPlayer mediaPlayer = MediaPlayer.create(this,
				R.raw.true_false_1 + new Random().nextInt(4));
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						mediaPlayer.release();
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
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						mediaPlayer.release();
					}
				}, mediaPlayer.getDuration());
			}
		});
	}

	private boolean checkAnswer() {
		boolean result = false;
		boolean check0 = !txtPhilologyJoinDestination0.getText().equals("")
				|| strContent[1].equals("");
		boolean check1 = !txtPhilologyJoinDestination1.getText().equals("")
				&& strContent[2].equals("") && strContent[0].equals("");
		if (check0 && check1) {
			result = true;
			setEnableButton(false);
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					final MediaPlayer mediaPlayer = MediaPlayer.create(context,
							startMfx + 3);
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							mediaPlayer.start();
							handler.postDelayed(new Runnable() {

								@Override
								public void run() {
									setEnableButton(true);
									nextContent();
									updateContent();
									mediaPlayer.release();
								}
							}, mediaPlayer.getDuration());
						}
					});
				}
			}, 1000);
		}
		return result;
	}

	private void setEnableText() {
		txtPhilologyJoinSource0.setEnabled(true);
		txtPhilologyJoinSource1.setEnabled(true);
		txtPhilologyJoinSource2.setEnabled(true);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View viewSource, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(
					viewSource);
			viewSource.startDrag(null, dragShadowBuilder, viewSource, 0);
			return true;
		}
		return false;
	}
}
