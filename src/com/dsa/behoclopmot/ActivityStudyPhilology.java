package com.dsa.behoclopmot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityStudyPhilology extends Activity implements OnClickListener {

	private Button btnAspSpeak;
	private Button btnAspJoin;
	private Button btAspCatch;
	private ImageButton btnAspQuit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_philology);
		create();
	}

	private void create() {
		btnAspJoin = (Button) findViewById(R.id.btnAspJoin);
		btnAspJoin.setOnClickListener(this);
		btAspCatch = (Button) findViewById(R.id.btnAspCatch);
		btAspCatch.setOnClickListener(this);
		btnAspSpeak = (Button) findViewById(R.id.btnAspSpeak);
		btnAspSpeak.setOnClickListener(this);
		btnAspQuit = (ImageButton) findViewById(R.id.btnAspQuit);
		btnAspQuit.setOnClickListener(this);
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
		case R.id.btnAspJoin:
			AdapterLesson.typeOfLesson = AdapterLesson.TYPE_JOIN;
			startActivity(new Intent(this, ActivityStudyLesson.class));
			break;
		case R.id.btnAspSpeak:
			AdapterLesson.typeOfLesson = AdapterLesson.TYPE_SPEAK;
			startActivity(new Intent(this, ActivityStudyLesson.class));
			break;
		case R.id.btnAspCatch:
			startActivity(new Intent(this, ActivityPhilologyCatch.class));
			break;
		case R.id.btnAspQuit:
			finish();
			Dsa.showAd();
			break;
		default:
			break;
		}
	}
}
