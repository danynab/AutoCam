package com.raa.autocam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.raa.autocam.andAr.CustomActivity;

public class MainActivity extends Activity {
	
	Integer segundos = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button boton = (Button) findViewById(R.id.MainActivity_boton_comenzar);
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				segundos = obtenerSegundos();
				Toast.makeText(MainActivity.this, segundos+"", Toast.LENGTH_SHORT).show();
				//TODO hacer algo con los segundos
				Intent i = new Intent(MainActivity.this, CustomActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public Integer obtenerSegundos() {
		RadioGroup radio = (RadioGroup) findViewById(R.id.MainActivity_radiogroup_segundos);
		Integer seleccionado = radio.getCheckedRadioButtonId();
		switch (seleccionado) {
			case R.id.MainActivity_radio_3:
				return 3;
			case R.id.MainActivity_radio_5:
				return 5;
			case R.id.MainActivity_radio_10:
				return 10;
			default:
				return null;
		}
	}
}
