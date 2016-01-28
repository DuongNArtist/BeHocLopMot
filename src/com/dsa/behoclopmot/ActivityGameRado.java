package com.dsa.behoclopmot;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsa.behoclopmot.games.GameRado;
import com.dsa.behoclopmot.games.GameSurface;
import com.dsa.behoclopmot.utilities.Dsa;
import com.dsa.behoclopmot.utilities.Gfx;
import com.dsa.behoclopmot.utilities.Mfx;

public class ActivityGameRado extends Activity implements OnClickListener {

	private ImageButton btnAgrQuit;
	private ImageButton btnAgrReplay;
	private FrameLayout frmAgrSurface;
	private LinearLayout lnrAgrGameOver;
	private TextView txtAgrLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_rado);
		lnrAgrGameOver = (LinearLayout) findViewById(R.id.lnrAgrGameOver);
		frmAgrSurface = (FrameLayout) findViewById(R.id.frmAgrSurface);
		txtAgrLoading = (TextView) findViewById(R.id.txtAgrLoading);
		btnAgrQuit = (ImageButton) findViewById(R.id.btnAgrQuit);
		btnAgrQuit.setOnClickListener(this);
		btnAgrReplay = (ImageButton) findViewById(R.id.btnAgrReplay);
		btnAgrReplay.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		new AsyncTask<String, Integer, Void>() {
			@Override
			protected void onPreExecute() {
				lnrAgrGameOver.setVisibility(LinearLayout.GONE);
			};

			@Override
			protected Void doInBackground(String... params) {
				Gfx.loadNumbers();
				Gfx.loadRados();
				Mfx.loadGame();
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				frmAgrSurface.addView(new GameSurface(getBaseContext(),
						new Runnable() {

							@Override
							public void run() {
								if (!GameSurface.gameRado.isPlaying()) {
									lnrAgrGameOver
											.setVisibility(LinearLayout.VISIBLE);
								}
							}
						}, GameSurface.GAME_RADO));
				txtAgrLoading.setVisibility(TextView.GONE);
				Mfx.playNewMusic();
			};

		}.execute();
	}

	@Override
	protected void onPause() {
		super.onPause();
		frmAgrSurface.removeAllViews();
		Mfx.mediaPlayer.stop();
		Gfx.releaseNumbers();
		Gfx.releaseRados();
		Mfx.releaseGame();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnAgrQuit:
			finish();
			Dsa.showAd();
			break;
		case R.id.btnAgrReplay:
			frmAgrSurface.setVisibility(LinearLayout.GONE);
			GameSurface.gameRado = new GameRado();
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		return;
	}
}
