package com.raa.autocam.andAr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.raa.autocam.PuenteActivity;

import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;

public class CustomActivity extends AndARActivity {

	CustomObject someObject;
	ARToolkit artoolkit;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		try {
			// register a object for each marker type
			artoolkit = super.getArtoolkit();
			someObject = new CustomObject("cube", "android.patt", 80.0,
					new double[] { 0, 0 }, getApplicationContext());
			artoolkit.registerARObject(someObject);

			// optional, may be set to null
			CustomRenderer renderer = new CustomRenderer(someObject, this);
			// or might be omited
			super.setNonARRenderer(renderer);
		} catch (AndARException ex) {
			// handle the exception, that means: show the user what happened
			System.out.println("");
		}
		startPreview();
	}

	public void objectoDetectado() {
		Intent i = new Intent(CustomActivity.this, PuenteActivity.class);
		startActivity(i);
		finish();
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
}
