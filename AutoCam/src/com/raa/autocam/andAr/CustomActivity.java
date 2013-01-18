package com.raa.autocam.andAr;

import java.util.Locale;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;

public class CustomActivity extends AndARActivity implements TextToSpeech.OnInitListener{
	CustomObject someObject;
	ARToolkit artoolkit;
	TextToSpeech tts;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		tts = new TextToSpeech(this, this);
		//CustomRenderer renderer = new CustomRenderer(getApplicationContext(), getWindowManager().getDefaultDisplay());//optional, may be set to null
		
		try {
			//register a object for each marker type
			artoolkit = super.getArtoolkit();
			someObject = new CustomObject("cube", "android.patt", 80.0, new double[]{0,0}, getApplicationContext());
			artoolkit.registerARObject(someObject);
			
			CustomRenderer renderer = new CustomRenderer(someObject, tts, this);//optional, may be set to null
			super.setNonARRenderer(renderer); //or might be omited
		} catch (AndARException ex){
			//handle the exception, that means: show the user what happened
			System.out.println("");
		}		
	}
	
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus)
			startPreview();
	}



	@Override
    public void onPause() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

	/**
	 * Inform the user about exceptions that occurred in background threads.
	 * This exception is rather severe and can not be recovered from.
	 * TODO Inform the user and shut down the application.
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		finish();
	}

	
	public void onInit(int status) {
	     if (status == TextToSpeech.SUCCESS) {
	    	 int result = tts.setLanguage(new Locale("es"));
	         if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	        	 Log.e("ARAndARTest", "This Language is not supported");
	         } else {
	        	 Log.d("ARAndARTest", "Initilization OK");
	         }
	     }
	}
	
}
