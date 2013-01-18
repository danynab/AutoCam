package com.raa.autocam.andAr;

import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.raa.autocam.PuenteActivity;

import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;

public class CustomActivity extends AndARActivity implements
		TextToSpeech.OnInitListener {
	private CustomObject someObject;
	private ARToolkit artoolkit;
	private TextToSpeech tts;
	private int segundos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tts = new TextToSpeech(this, this);
		segundos = getIntent().getExtras().getInt("segundos");
		try {
			// register a object for each marker type
			artoolkit = super.getArtoolkit();
			someObject = new CustomObject("cube", "android.patt", 80.0,
					new double[] { 0, 0 }, getApplicationContext());
			artoolkit.registerARObject(someObject);

			// optional, may be set to null
			CustomRenderer renderer = new CustomRenderer(someObject, tts,
					segundos, this);
			// or might be omited
			super.setNonARRenderer(renderer);
		} catch (AndARException ex) {
			// handle the exception, that means: show the user what happened
			System.out.println("");
		}
		startPreview();
	}

	public void objectoDetectado() {
		finish();
		Intent i = new Intent(this, PuenteActivity.class);
		i.putExtra("segundos", segundos);
		startActivity(i);
	}

	@Override
	protected void onDestroy() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	/**
	 * Inform the user about exceptions that occurred in background threads.
	 * This exception is rather severe and can not be recovered from. TODO
	 * Inform the user and shut down the application.
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		finish();
	}

	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(new Locale("es"));
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("ARAndARTest", "This Language is not supported");
			} else {
				Log.d("ARAndARTest", "Initilization OK");
			}
		}
	}
}
