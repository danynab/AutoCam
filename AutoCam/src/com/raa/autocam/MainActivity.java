package com.raa.autocam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;

import com.raa.autocam.andAr.CustomActivity;

public class MainActivity extends Activity {
	
	private int segundos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button boton = (Button) findViewById(R.id.MainActivity_boton_comenzar);
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				segundos = obtenerSegundos();
				Intent i = new Intent(MainActivity.this, CustomActivity.class);
				i.putExtra("segundos", segundos);
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.MenuItem_acerca_de:
			mostrarAcercaDe();
			return true;
		default:
			return false;
		}
	}

	private void mostrarAcercaDe() {
		Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
		startActivity(intent);
	}

	public int obtenerSegundos() {
		RadioGroup radio = (RadioGroup) findViewById(R.id.MainActivity_radiogroup_segundos);
		Integer seleccionado = radio.getCheckedRadioButtonId();
		switch (seleccionado) {
			case R.id.MainActivity_radio_5:
				return 5;
			case R.id.MainActivity_radio_8:
				return 8;
			case R.id.MainActivity_radio_10:
				return 10;
			default:
				return 0;
		}
	}
}
