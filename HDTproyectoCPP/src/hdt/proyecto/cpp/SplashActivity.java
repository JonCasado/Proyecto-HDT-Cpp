package hdt.proyecto.cpp;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity {

	// Sirve para establecer el tiempo que queremos que dure esta actividad en milisegundos
	private static final long SPLASH_SCREEN_DELAY = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Indicamos la orientacion del terminal
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Escondemos la barra de titulo
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.splash_screen);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				// Iniciamos la siguiente actividad
				Intent mainIntent = new Intent().setClass(
						SplashActivity.this, Pantalla_inicio.class);
				startActivity(mainIntent);

				// Cerramos el activity de forma que no podamos volver a el 
				// pulsando el boton atras
				finish();
			}
		};

		// Simulamos una carga de la aplicación
		Timer timer = new Timer();
		timer.schedule(task, SPLASH_SCREEN_DELAY);
	}
}//Fin de la clase SplashActivity