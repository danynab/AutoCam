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

	/**
	 * Light definitions
	 */
	private CustomObject someObject;
	private TextToSpeech tts;
	private boolean finished;
	private boolean hablado;
	private CustomActivity context;
	private String texto;
	private int segundos;

	public CustomRenderer(CustomObject someObject, TextToSpeech tts,
			int segundos, CustomActivity context) {
		this.someObject = someObject;
		this.tts = tts;
		this.context = context;
		this.segundos = segundos;
	}

	/**
	 * Directly called before each object is drawn. Used to setup lighting and
	 * other OpenGL specific things.
	 */
	public final void setupEnv(GL10 gl) {
		if (this.someObject.isVisible()) {
			if (!tts.isSpeaking()) {
				if (!finished) {
					texto = "Te he encontrado. En " + String.valueOf(segundos)
							+ " segundos se sacará la foto. Comienza a contar.";
					this.tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
					hablado = true;
				}
				if (hablado)
					finished = true;
			}
			if (hablado && finished) {
				context.objectoDetectado();
				hablado = false;
			}
		} else {
			this.tts.stop();
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
