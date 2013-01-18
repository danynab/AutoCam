package com.raa.autocam;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.widget.TextView;

public class PuenteActivity extends Activity implements OnInitListener {
	
	private TextView texto_cuenta;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puente);
		texto_cuenta = (TextView) findViewById(R.id.texto_cuenta);
		
		reproducirCuenta(5);
	}
	
	private void reproducirCuenta(int cuenta) {
		TextToSpeech tts = new TextToSpeech(this,this);
			
		
		String texto = "Te he encontrado.";
		texto_cuenta.setText(texto);
		tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
		while (tts.isSpeaking());
		for (int i = cuenta; i >= 0; i--) {
			if (i == 0)
				texto = "Sonríe";
			else
				texto = String.valueOf(i);
			texto_cuenta.setText(texto);
			tts.speak(texto, TextToSpeech.QUEUE_ADD, null);
			while (tts.isSpeaking());
		}
		tts.stop();
		tts.shutdown();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_puente, menu);
		return true;
	}

	@Override
	public void onInit(int status) {
		/*if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(new Locale("es"));
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("AutoCam", "This Language is not supported");
			} else {
				Log.d("AutoCam", "Initilization OK");
			}
		}*/
	}
	
	@Override
	public void onPause() {
		super.onPause();
		finish();
	}

}
