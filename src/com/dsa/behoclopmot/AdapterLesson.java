package com.dsa.behoclopmot;

import com.dsa.behoclopmot.utilities.Dsa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class AdapterLesson extends BaseAdapter {

	public static final int TYPE_JOIN = 0;
	public static final int TYPE_SPEAK = 1;

	public static int indexOfLesson = 0;
	public static int typeOfLesson = 0;
	public static String[] strLessonName;

	private Context context;

	public AdapterLesson(Context context) {
		this.context = context;
		switch (typeOfLesson) {
		case TYPE_JOIN:
			strLessonName = context.getResources().getStringArray(
					R.array.study_philology_join_lesson);
			break;
		case TYPE_SPEAK:
			strLessonName = context.getResources().getStringArray(
					R.array.study_philology_speak_lesson);
			break;
		default:
			break;
		}
	}

	@Override
	public int getCount() {
		return strLessonName.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.layout_lesson,
					viewGroup, false);
		}
		Button btnLessonName = (Button) view.findViewById(R.id.btnLessonName);
		btnLessonName.setBackgroundResource(R.drawable.button_0
				+ (position % 5));
		btnLessonName.setText(strLessonName[position]);
		btnLessonName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				indexOfLesson = position;
				switch (typeOfLesson) {
				case TYPE_JOIN:
					context.startActivity(new Intent(context,
							ActivityPhilologyJoin.class));
					break;
				case TYPE_SPEAK:
					context.startActivity(new Intent(context,
							ActivityPhilologySpeak.class));
					break;
				default:
					break;
				}
			}
		});
		ImageView imgLessonPassed = (ImageView) view
				.findViewById(R.id.imgLessonPassed);
		if (Dsa.isPassed(typeOfLesson, position)) {
			imgLessonPassed.setVisibility(ImageView.VISIBLE);
		} else {
			imgLessonPassed.setVisibility(ImageView.GONE);
		}
		return view;
	}
}
