package com.dsa.behoclopmot;

import com.dsa.behoclopmot.utilities.Dsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ActivityStudy extends Activity implements OnClickListener {

	private ImageButton btnAsPhilology;
	private ImageButton btnAsMath;
	private ImageButton btnAsKnowledge;
	private ImageButton btnAsQuit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study);
		btnAsPhilology = (ImageButton) findViewById(R.id.btnAsPhilology);
		btnAsPhilology.setOnClickListener(this);
		btnAsMath = (ImageButton) findViewById(R.id.btnAsMath);
		btnAsMath.setOnClickListener(this);
		btnAsKnowledge = (ImageButton) findViewById(R.id.btnAsKnowledge);
		btnAsKnowledge.setOnClickListener(this);
		btnAsQuit = (ImageButton) findViewById(R.id.btnAsQuit);
		btnAsQuit.setOnClickListener(this);
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
		case R.id.btnAsPhilology:
			startActivity(new Intent(this, ActivityStudyPhilology.class));
			break;
		case R.id.btnAsMath:
			startActivity(new Intent(this, ActivityStudyMath.class));
			break;
		case R.id.btnAsKnowledge:
			startActivity(new Intent(this, ActivityStudyKnowledge.class));
			break;
		case R.id.btnAsQuit:
			finish();
			Dsa.showAd();
			break;
		default:
			break;
		}
	}
}
