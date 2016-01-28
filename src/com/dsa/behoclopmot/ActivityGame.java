package com.dsa.behoclopmot;

import com.dsa.behoclopmot.utilities.Dsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityGame extends Activity implements OnClickListener {

	private Button btnAgPoke;
	private Button btnAgRado;
	private ImageButton btnAgQuit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		btnAgPoke = (Button) findViewById(R.id.btnAgPoke);
		btnAgPoke.setOnClickListener(this);
		btnAgRado = (Button) findViewById(R.id.btnAgRado);
		btnAgRado.setOnClickListener(this);
		btnAgQuit = (ImageButton) findViewById(R.id.btnAgQuit);
		btnAgQuit.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnAgPoke:
			startActivity(new Intent(this, ActivityGamePoke.class));
			break;
		case R.id.btnAgRado:
			startActivity(new Intent(this, ActivityGameRado.class));
			break;
		case R.id.btnAgQuit:
			finish();
			Dsa.showAd();
			break;
		default:
			break;
		}
	}
}
