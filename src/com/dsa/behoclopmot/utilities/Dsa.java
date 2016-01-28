package com.dsa.behoclopmot.utilities;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioManager;

import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;

public class Dsa extends Application {

	public static final String KEY_AD = "ca-app-pub-9404075236503358/1493364020";
	public static final String SP_NAME = "DuongNArtist";
	public static final String KEY_LESSON = "Lesson";
	public static Dsa dsa;
	public static AssetManager assetManager;
	public static AudioManager audioManager;
	public static Resources resources;
	public static InterstitialAd ad;
	public static SharedPreferences sharedPreferences;
	public static Editor editor;
	public static int counter;

	@Override
	public void onCreate() {
		super.onCreate();
		dsa = this;
		resources = getResources();
		assetManager = getAssets();
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
				audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), 0);
		sharedPreferences = getContext().getSharedPreferences(SP_NAME,
				Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}

	public static void count() {
		counter++;
		if (counter == 2) {
			counter = 0;
			showAd();
		}
	}

	public static void createAd(Activity activity) {
		counter = 0;
		ad = new InterstitialAd(activity, Dsa.KEY_AD);
		ad.loadAd(new AdRequest());
	}

	public static void showAd() {
		if (ad.isReady()) {
			ad.show();
		}
	}

	public static Context getContext() {
		return dsa;
	}

	public static Dsa getDsa() {
		return dsa;
	}

	public static void setPassed(int type, int index, boolean value) {
		editor.putBoolean(KEY_LESSON + type + index, value);
		editor.commit();
	}

	public static boolean isPassed(int type, int index) {
		return sharedPreferences.getBoolean(KEY_LESSON + type + index, false);
	}
}
