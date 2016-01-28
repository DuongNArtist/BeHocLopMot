package com.dsa.behoclopmot;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastDuong extends Toast {

	public ToastDuong(Context context, int score) {
		super(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.layout_knowledge, null, false);
		int i = (score % 120) / 25;
		view.setBackgroundResource(R.drawable.background_11);
		TextView txtMsgScore = (TextView) view.findViewById(R.id.txtMsgScore);
		TextView txtMsgLevel = (TextView) view.findViewById(R.id.txtMsgLevel);
		TextView txtMsgMessage = (TextView) view
				.findViewById(R.id.txtMsgMessage);
		String[] str = context.getResources().getStringArray(
				R.array.knowledge_message_0 + i);
		txtMsgScore.setText(score + "");
		txtMsgLevel.setText(str[0]);
		txtMsgMessage.setText(str[1]);
		setView(view);
		setGravity(Gravity.CENTER, 0, 0);
		setDuration(Toast.LENGTH_LONG);
		show();
	}
}
