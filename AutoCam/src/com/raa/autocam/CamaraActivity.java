package com.raa.autocam;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.Toast;

public class CamaraActivity extends Activity implements SurfaceHolder.Callback{

	private Integer idCamara = -1;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camara);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		if (!existeCamara()) {
			Toast.makeText(this, R.string.error_no_camara, Toast.LENGTH_LONG)
					.show();
			finish();
		} else {
			idCamara = buscarCamaraTrasera();
			if (idCamara == null) {
				Toast.makeText(this, R.string.error_no_camara_trasera, 
						Toast.LENGTH_LONG).show();
			} else {
				surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
				
				surfaceHolder = surfaceView.getHolder();
				surfaceHolder.addCallback(this);
				surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

				
			}
		}
	}

	private void sacarFoto() {
		//camara.takePicture(null, null, new ManejadorFotos(getApplicationContext()));
	}

	private boolean existeCamara() {
		return getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA);
	}

	/**
	 * @return El id de la cámara trasera del dispositivo. Null si no hay camara
	 *         trasera.
	 */
	private Integer buscarCamaraTrasera() {
		Integer idCamara = -1;
		Integer numCamaras = Camera.getNumberOfCameras();
		Integer i = 0;

		while (i < numCamaras && idCamara == -1) {
			CameraInfo infoCamara = new CameraInfo();
			Camera.getCameraInfo(i, infoCamara);
			if (infoCamara.facing == CameraInfo.CAMERA_FACING_BACK) {
				idCamara = i;
			}
		}
		if (idCamara == -1) {
			return null;
		} else {
			return idCamara;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Camera camara = Camera.open(idCamara);
			try {
				camara.setPreviewDisplay(holder);
				camara.startPreview();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//sacarFoto();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		//camara.stopPreview();
		//camara.release();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	
	
}