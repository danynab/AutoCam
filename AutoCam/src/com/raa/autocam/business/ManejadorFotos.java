package com.raa.autocam.business;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.raa.autocam.R;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;



public class ManejadorFotos implements PictureCallback {

	private final Context context;

	public ManejadorFotos(Context context) {
		this.context = context;
	}

	public void onPictureTaken(byte[] data, Camera camera) {
		File directorioImagenes = 
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File carpeta = new File(directorioImagenes, "AutoCam");

	
		if (!carpeta.exists() && !carpeta.mkdirs()) {
			Log.d("PRUEBAS", "Error. No se puede crear la carpeta");
			Toast.makeText(context, "Error. No se puede crear la carpeta", 
					Toast.LENGTH_SHORT).show();
			return;
		}

		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyymmdd-hhmmss");
		String fecha = formatoFecha.format(new Date());
		String nombreImagen = "Imagen_" + fecha + ".jpg";
		String nombreFichero = carpeta.getPath() + File.separator + nombreImagen;

		File pictureFile = new File(nombreFichero);
		try {
			FileOutputStream stream = new FileOutputStream(pictureFile);
			stream.write(data);
			stream.close();
			Toast.makeText(context, "Imagen guardada:" + nombreImagen, Toast.LENGTH_SHORT).show();
		} catch (Exception error) {
			Log.d("PRUEBAS", "Error. La imagen" + nombreFichero + 
					"no ha podido ser guardada: " + error.getMessage());
			Toast.makeText(context, "Error. La imagen no ha podido ser guardada", 
					Toast.LENGTH_SHORT).show();
		}
	}


}
