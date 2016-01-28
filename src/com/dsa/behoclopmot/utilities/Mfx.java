package com.dsa.behoclopmot.utilities;

import java.io.IOException;
import java.util.Random;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.SparseArray;

import com.dsa.behoclopmot.R;

@SuppressLint("UseSparseArrays")
public class Mfx {

	public static final int SRC_QUALITY = 0;
	public static final int LEFT_VOLUME = 1;
	public static final int RIGHT_VOLUME = 1;
	public static final int PRIORITY = 1;
	public static final int LOOP = 0;
	public static final int RATE = 1;

	public static final int NUMBER_OF_CHARS = 30;
	public static final int NUMBER_OF_GAMES = 3;
	public static final int NUMBER_OF_FINDS = 4;
	public static final int NUMBER_OF_COUNTS = 36;
	public static final int NUMBER_OF_PRACTICES = 28;

	public static int gameMusic;
	public static MediaPlayer mediaPlayer;

	public static SoundPool spChar;
	public static SparseArray<Integer> saChar;

	public static SoundPool spFind;
	public static SparseArray<Integer> saFind;

	public static SoundPool spGame;
	public static SparseArray<Integer> saGame;

	public static SoundPool spTrueFalse;
	public static SparseArray<Integer> saTrueFalse;

	public static SoundPool spTopCenterBottom;
	public static SparseArray<Integer> saTopCenterBottom;

	public static SoundPool spLeftCenterRight;
	public static SparseArray<Integer> saLeftCenterRight;

	public static SoundPool spHigherLower;
	public static SparseArray<Integer> saHigherLower;

	public static SoundPool spSameDifferent;
	public static SparseArray<Integer> saSameDifferent;

	public static SoundPool spRecognize;
	static SparseArray<Integer> saRecognize;

	public static SoundPool spMoreLess;
	public static SparseArray<Integer> saMoreLess;

	public static SoundPool spMaxMin;
	public static SparseArray<Integer> saMaxMin;

	public static SoundPool spCount;
	public static SparseArray<Integer> saCount;

	public static SoundPool spPractice;
	public static SparseArray<Integer> saPractice;

	public static SoundPool spNumber;
	public static SparseArray<Integer> saNumber;

	public static SoundPool spColor;
	public static SparseArray<Integer> saColor;

	public static SoundPool spShape;
	public static SparseArray<Integer> saShape;

	public static void playNewMusic() {
		mediaPlayer = MediaPlayer.create(Dsa.getContext(), R.raw.game_0
				+ new Random().nextInt(5));
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
	}

	public static void loadGame() {
		String path = "mfx/games/voice_";
		spGame = new SoundPool(NUMBER_OF_GAMES, AudioManager.STREAM_MUSIC,
				SRC_QUALITY);
		saGame = new SparseArray<Integer>();
		for (int i = 0; i < NUMBER_OF_GAMES; i++) {
			try {
				saGame.put(i, spGame.load(
						Dsa.assetManager.openFd(path + i + ".ogg"), PRIORITY));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void playGame(int index) {
		spGame.play(saGame.get(index), LEFT_VOLUME, RIGHT_VOLUME, PRIORITY,
				LOOP, RATE);
	}

	public static void releaseGame() {
		spGame = null;
		saGame = null;
	}

	public static void loadTrueFalse() {
		String path = "mfx/study/true_false/voice_";
		spTrueFalse = new SoundPool(5, AudioManager.STREAM_MUSIC, SRC_QUALITY);
		saTrueFalse = new SparseArray<Integer>();
		for (int i = 0; i < 5; i++) {
			try {
				saTrueFalse.put(i, spTrueFalse.load(
						Dsa.assetManager.openFd(path + i + ".mp3"), PRIORITY));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void releaseTrueFalse() {
		saTrueFalse = null;
		spTrueFalse = null;
	}

	public static void playTrueFalse(int index) {
		spTrueFalse.play(saTrueFalse.get(index), LEFT_VOLUME, RIGHT_VOLUME,
				PRIORITY, LOOP, RATE);
	}
}
