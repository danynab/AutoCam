package com.raa.autocam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PuenteActivity extends Activity {
	
	private int segundos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_puente);
		segundos = getIntent().getExtras().getInt("segundos");
		lanzarCapturador();	
	}
	
	private void lanzarCapturador() {
		Thread hilo = new Thread() {
			@Override
			public void run() {
				try {
					sleep(segundos * 1000);
					Intent i = new Intent(PuenteActivity.this, CamaraActivity.class);
					startActivity(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		hilo.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	
}
