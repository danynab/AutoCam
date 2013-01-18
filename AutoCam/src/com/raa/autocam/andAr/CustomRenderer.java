package com.raa.autocam.andAr;

import javax.microedition.khronos.opengles.GL10;

import android.speech.tts.TextToSpeech;
import edu.dhbw.andar.AndARRenderer;
import edu.dhbw.andar.interfaces.OpenGLRenderer;

/**
 * A custom OpenGL renderer, that gets registered to the {@link AndARRenderer}.
 * It allows you to draw non Augmented Reality stuff, and setup the OpenGL
 * environment.
 * 
 * @author tobi
 * 
 */
public class CustomRenderer implements OpenGLRenderer {

	private final int TIEMPO = 5;

	/**
	 * Light definitions
	 */
	CustomObject someObject;
	TextToSpeech tts;
	boolean finished;
	CustomActivity context;
	String texto;
	int momento;

	public CustomRenderer(CustomObject someObject, TextToSpeech tts,
			CustomActivity context) {
		this.someObject = someObject;
		this.tts = tts;
		this.context = context;
	}

	/**
	 * Directly called before each object is drawn. Used to setup lighting and
	 * other OpenGL specific things.
	 */
	public final void setupEnv(GL10 gl) {
		if (this.someObject.isVisible()) {
			if (!this.tts.isSpeaking()) {
				if (!finished) {
					texto = "Te he encontrado.";
					this.tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
					finished = true;
					momento = TIEMPO + 1;
				}
			}
			if (!this.tts.isSpeaking() && finished) {
				if (momento == 0)
					context.objectoDetectado();
				else {
					if (momento == 1)
						texto = "Sonríe";
					else
						texto = String.valueOf(momento - 1);
					this.tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
					momento--;
				}
			}
		} else {
			this.tts.stop();
			finished = false;
		}
	}

	@Override
	public void draw(GL10 arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initGL(GL10 arg0) {
		// TODO Auto-generated method stub

	}

}
