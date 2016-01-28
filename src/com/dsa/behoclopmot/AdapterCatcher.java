package com.dsa.behoclopmot;

import java.util.Random;

import com.dsa.behoclopmot.utilities.Dsa;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AdapterCatcher extends BaseAdapter {

	private Context context;
	private String[] strCatcher;
	private String strOption;

	public AdapterCatcher(Context context, int index) {
		this.context = context;
		strCatcher = context.getResources().getStringArray(
				R.array.study_philology_catch);
		int other = 0;
		do {
			other = new Random().nextInt(strCatcher.length);
		} while (other == index);
		strOption = mixString(strCatcher[index], strCatcher[other]);
	}

	private String mixString(String a, String b) {
		final int aLength = a.length();
		final int bLength = b.length();
		final int min = Math.min(aLength, bLength);
		final StringBuilder sb = new StringBuilder(aLength + bLength);
		for (int i = 0; i < min; i++) {
			sb.append(a.charAt(i));
			sb.append(b.charAt(i));
		}
		if (aLength > bLength) {
			sb.append(a, bLength, aLength);
		} else if (aLength < bLength) {
			sb.append(b, aLength, bLength);
		}
		return sb.toString().replaceAll("\\s", "");
	}

	@Override
	public int getCount() {
		return strOption.length();
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
			view = LayoutInflater.from(context).inflate(
					R.layout.layout_catcher, viewGroup, false);
		}
		final Button btnCatcher = (Button) view.findViewById(R.id.btnCatcher);
		btnCatcher.setBackgroundResource(R.drawable.button_0 + (position % 6));
		btnCatcher.setText(strOption.substring(position, position + 1));
		btnCatcher.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < ActivityPhilologyCatch.txtApcDst00.length; i++) {
					if (ActivityPhilologyCatch.txtApcDst00[i].getText()
							.toString().equals(btnCatcher.getText().toString())) {
						ActivityPhilologyCatch.txtApcDst00[i]
								.setVisibility(TextView.VISIBLE);
					}
				}
				if (ActivityPhilologyCatch.check()) {
					ActivityPhilologyCatch.setEnable(false);
					final MediaPlayer mediaPlayer = MediaPlayer.create(
							Dsa.getContext(),
							R.raw.true_false_1 + new Random().nextInt(4));
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							mediaPlayer.start();
							ActivityPhilologyCatch.handler.postDelayed(
									new Runnable() {

										@Override
										public void run() {
											mediaPlayer.release();
										}
									}, mediaPlayer.getDuration());
						}
					});
					ActivityPhilologyCatch.handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							ActivityPhilologyCatch.setEnable(true);
							ActivityPhilologyCatch.ask();
							ActivityPhilologyCatch.speak();
						}
					}, 1000);
				} else {
					Dsa.count();
				}
			}
		});
		return view;
	}
}
