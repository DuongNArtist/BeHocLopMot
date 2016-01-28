package com.dsa.behoclopmot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.dsa.behoclopmot.utilities.Dsa;

public class ActivityStudyLesson extends Activity implements OnClickListener {

	private TextView txtAslTitle;
	private ListView lstAslName;
	private ImageButton btnAslReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_lesson);
		create();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Dsa.createAd(this);
	}

	private void create() {
		txtAslTitle = (TextView) findViewById(R.id.txtAslTitle);
		if (AdapterLesson.typeOfLesson == AdapterLesson.TYPE_JOIN) {
			txtAslTitle.setText(getResources().getString(
					R.string.philology_join));
		} else {
			txtAslTitle.setText(getResources().getString(
					R.string.philology_speak));
		}
		lstAslName = (ListView) findViewById(R.id.lstAslName);
		btnAslReturn = (ImageButton) findViewById(R.id.btnAslReturn);
		btnAslReturn.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		lstAslName.setAdapter(new AdapterLesson(this));
	}

	@Override
	public void onBackPressed() {
		return;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnAslReturn:
			finish();
			Dsa.showAd();
			break;
		default:
			break;
		}
	}
}
