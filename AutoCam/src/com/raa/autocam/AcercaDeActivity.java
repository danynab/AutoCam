package com.raa.autocam;

import android.app.Activity;
import android.os.Bundle;

public class AcercaDeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acerca_de);
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	

}
