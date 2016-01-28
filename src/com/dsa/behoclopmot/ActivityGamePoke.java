package com.dsa.behoclopmot;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dsa.behoclopmot.games.GameSurface;
import com.dsa.behoclopmot.utilities.Dsa;
import com.dsa.behoclopmot.utilities.Gfx;
import com.dsa.behoclopmot.utilities.Mfx;

public class ActivityGamePoke extends Activity implements OnClickListener {

	private ImageButton btnAgpQuit;
	private FrameLayout frmAgpSurface;
	private TextView txtAgpLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_poke);
		txtAgpLoading = (TextView) findViewById(R.id.txtAgpLoading);
		frmAgpSurface = (FrameLayout) findViewById(R.id.frmAgpSurface);
		btnAgpQuit = (ImageButton) findViewById(R.id.btnAgpQuit);
		btnAgpQuit.setOnClickListener(this);
		btnAgpQuit.setVisibility(Button.INVISIBLE);
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

			};

			@Override
			protected Void doInBackground(String... params) {
				Gfx.loadBackgrounds();
				Gfx.loadBalls();
				Gfx.loadPokes();
				Gfx.loadNumbers();
				Mfx.loadGame();
				return null;
			};

			@Override
			protected void onPostExecute(Void result) {
				Mfx.playNewMusic();
				txtAgpLoading.setVisibility(TextView.GONE);
				btnAgpQuit.setVisibility(Button.VISIBLE);
				frmAgpSurface.addView(new GameSurface(getBaseContext(), null,
						GameSurface.GAME_POKE));
			}
		}.execute();
	}

	@Override
	protected void onPause() {
		super.onPause();
		frmAgpSurface.removeAllViews();
		Mfx.mediaPlayer.stop();
		Gfx.releaseBackrounds();
		Gfx.releaseBalls();
		Gfx.releaseNumbers();
		Gfx.releasePokes();
		Mfx.releaseGame();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnAgpQuit:
			finish();
			Dsa.showAd();
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
