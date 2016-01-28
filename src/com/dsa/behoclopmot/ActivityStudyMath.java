package com.dsa.behoclopmot;

import com.dsa.behoclopmot.utilities.Dsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityStudyMath extends Activity implements OnClickListener {

	private Button btnAsmAddSub10;
	private Button btnAsmAddSub100;
	private Button btnAsmCompare;
	private Button btnAsmTrueFalse;
	private Button btnAsmTest;
	private Button btnAsmEvenOdd;
	private Button btnAsmGuess;
	private Button btnAsmConstitute;
	private ImageButton btnAsmReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_math);
		create();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void create() {
		btnAsmReturn = (ImageButton) findViewById(R.id.btnAsmReturn);
		btnAsmReturn.setOnClickListener(this);
		btnAsmAddSub10 = (Button) findViewById(R.id.btnAsmAddSub10);
		btnAsmAddSub10.setOnClickListener(this);
		btnAsmAddSub100 = (Button) findViewById(R.id.btnAsmAddSub100);
		btnAsmAddSub100.setOnClickListener(this);
		btnAsmCompare = (Button) findViewById(R.id.btnAsmCompare);
		btnAsmCompare.setOnClickListener(this);
		btnAsmTrueFalse = (Button) findViewById(R.id.btnAsmTrueFalse);
		btnAsmTrueFalse.setOnClickListener(this);
		btnAsmTest = (Button) findViewById(R.id.btnAsmTest);
		btnAsmTest.setOnClickListener(this);
		btnAsmEvenOdd = (Button) findViewById(R.id.btnAsmEvenOdd);
		btnAsmEvenOdd.setOnClickListener(this);
		btnAsmGuess = (Button) findViewById(R.id.btnAsmGuess);
		btnAsmGuess.setOnClickListener(this);
		btnAsmConstitute = (Button) findViewById(R.id.btnAsmConstitute);
		btnAsmConstitute.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnAsmAddSub10:
			startActivity(new Intent(this, ActivityMathAddSub10.class));
			break;
		case R.id.btnAsmAddSub100:
			startActivity(new Intent(this, ActivityMathAddSub100.class));
			break;
		case R.id.btnAsmCompare:
			startActivity(new Intent(this, ActivityMathCompare.class));
			break;
		case R.id.btnAsmTrueFalse:
			startActivity(new Intent(this, ActivityMathTrueFalse.class));
			break;
		case R.id.btnAsmTest:
			startActivity(new Intent(this, ActivityMathCalculate.class));
			break;
		case R.id.btnAsmEvenOdd:
			startActivity(new Intent(this, ActivityMathEvenOdd.class));
			break;
		case R.id.btnAsmGuess:
			startActivity(new Intent(this, ActivityMathGuess.class));
			break;
		case R.id.btnAsmConstitute:
			startActivity(new Intent(this, ActivityMathConstitute.class));
			break;
		case R.id.btnAsmReturn:
			finish();
			Dsa.showAd();
			break;
		default:
			break;
		}
	}
}
