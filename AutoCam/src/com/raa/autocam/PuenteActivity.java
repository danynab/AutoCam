package com.raa.autocam;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class PuenteActivity extends Activity implements TextToSpeech.OnInitListener {
	
	private TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puente);

		tts = new TextToSpeech(this, this);
		
		Thread hilo = new Thread() {

			@Override
			public void run() {
				reproducirCuenta(5);
			}
			
		};
		hilo.start();
	}
	
	private void reproducirCuenta(int cuenta) {
		TextView texto_cuenta =  (TextView) findViewById(R.id.texto_cuenta);
		
		String texto = "Te he encontrado.";
		texto_cuenta.setText(texto);
		this.tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
		while (this.tts.isSpeaking());
		for (int i = cuenta; i >= 0; i--) {
			if (i == 0)
				texto = "Sonríe";
			else
				texto = String.valueOf(i);
			
			this.tts.speak(texto, TextToSpeech.QUEUE_ADD, null);
			texto_cuenta.setText(texto);
			while (this.tts.isSpeaking());
		}
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_puente, menu);
		return true;
	}

	@Override
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
	
	@Override
	public void onPause() {
		if (this.tts != null) {
			this.tts.stop();
			this.tts.shutdown();
		}
		super.onPause();
		finish();
	}

}
