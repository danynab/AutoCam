package com.raa.autocam.andAr;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import edu.dhbw.andar.ARObject;

/**
 * An example of an AR object being drawn on a marker.
 * 
 * @author tobi
 * 
 */
public class CustomObject extends ARObject {

	public CustomObject(String name, String patternName, double markerWidth,
			double[] markerCenter, Context context) {
		super(name, patternName, markerWidth, markerCenter);

	}

	public CustomObject(String name, String patternName, double markerWidth,
			double[] markerCenter, float[] customColor, Context context) {
		super(name, patternName, markerWidth, markerCenter);
	}

	@Override
	public void init(GL10 arg0) {
		// TODO Auto-generated method stub

	}

}
