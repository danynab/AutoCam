package com.raa.autocam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PuenteActivity extends Activity {

	private int segundos;
	private TextView texto_cuenta;
	private Hand handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_puente);
		
		texto_cuenta = (TextView) findViewById(R.id.texto_cuenta);
		
		handler = new Hand(texto_cuenta);
		
		segundos = getIntent().getExtras().getInt("segundos");
		
		lanzarCapturador();
	}

	@SuppressLint("HandlerLeak")
	private void lanzarCapturador() {
		Thread hilo = new Thread() {
			@Override
			public void run() {
				try {
					String text = null;
					for (int i = segundos; i >= 0; i--) {
						if (i == 0)
							text = "Sonríe :D";
						else
							text = String.valueOf(i);
						Message mensaje = new Message();
						mensaje.obj = text;
						handler.sendMessage(mensaje);
						sleep(1000);
					}

					Intent i = new Intent(PuenteActivity.this,
							CamaraActivity.class);
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
	
	private class Hand extends Handler {
		TextView textView;

		public Hand(TextView textView) {
			this.textView = textView;
		}

		public void handleMessage(Message msg) {
			textView.setText(String.valueOf(msg.obj));
		}
	}

}
