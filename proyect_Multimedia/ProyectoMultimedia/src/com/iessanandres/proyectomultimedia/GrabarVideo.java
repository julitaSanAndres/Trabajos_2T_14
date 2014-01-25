package com.iessanandres.proyectomultimedia;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class GrabarVideo extends Activity implements TextToSpeech.OnInitListener  {

	/*
	 * sin permisos, de utilizacion de ningun tipo 1- en el diseño ponemos dos
	 * botones, para grabar, y reproducir el video 2- definimos los objetos a
	 * utilizar
	 */

	private Button bGrabar;
	private Button bReprod;
	private VideoView video;
	private TextToSpeech voz;

	// codigo de android para saber si se ha guardado bien el video
	final static int REQUEST_VIDEO_CAPTURED = 1;
	//guardar la ruta del video donde se guardará
	Uri rutaVideo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grabarvideo);

		bGrabar = (Button) findViewById(R.id.button1);
		bReprod = (Button) findViewById(R.id.button2);
		video = (VideoView) findViewById(R.id.videoView1);
		voz= new TextToSpeech(this,this);

		// //sin clicks en los botones del diseño
		// bGrabar.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		//
		// //llamar a la aplicacion interna del video propio del dispositivo
		// Intent intent = new Intent(
		// android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
		// startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);
		//
		// }
		// });
		//
		// bReprod.setOnClickListener(new Button.OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		//
		// //si pulsamos reproducir si n haber grabado nada, nos mostrará un
		// mensaje de
		// //no hay video, y en caso contrario, mostraria la reproduccion de
		// mismo, con su ruta
		// if (rutaVideo == null) {
		// Toast.makeText(MainActivity.this, "No hay Video",
		// Toast.LENGTH_SHORT).show();
		// } else {
		// Toast.makeText(MainActivity.this,
		// "Playback: " + rutaVideo.getPath(),
		// Toast.LENGTH_SHORT).show();
		// video.setVideoURI(rutaVideo);
		// video.start();
		// }
		// }
		// });
	}

	
	
	

	// llamar a la aplicacion interna del dispositivo, para grabar
	public void clickGrabar(View v) {
		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
		startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);

	}

	//si pulsamos reproducir sin haber grabado nada, nos mostrará un
	// mensaje de no hay video, y en caso contrario, mostraria la reproduccion de
	// mismo, y el mensaje con su ruta
	public void clickReproducir(View v) {
		
		if (rutaVideo == null) {
			voz.speak("No hay Video", TextToSpeech.QUEUE_FLUSH, null);

			Toast.makeText(GrabarVideo.this, "No hay Video",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(GrabarVideo.this,
					"Reproduciendo: " + rutaVideo.getPath(), Toast.LENGTH_SHORT).show();
			video.setVideoURI(rutaVideo);
			video.start();
		}
	}

	/*
	 * este metodo es para aceptar el guardado del video o no. si se guarda el
	 * video despues de realizado, se mostrará la ruta del video en caso de
	 * cancelar el guardado, se mostrará otro mensaje
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_VIDEO_CAPTURED) {
				rutaVideo = data.getData();
				Toast.makeText(this, rutaVideo.getPath(), Toast.LENGTH_SHORT).show();
			}
		} else if (resultCode == RESULT_CANCELED) {
			rutaVideo = null;
			Toast.makeText(this, "Ha cancelado el guardado", Toast.LENGTH_SHORT).show();
		}
	}





	//@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}
}
