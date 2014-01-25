package com.iessanandres.proyectomultimedia;

import android.R.layout;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Multimedia extends Activity implements TextToSpeech.OnInitListener {

	private ListView list;
	private TextToSpeech voz;
	private Opcion[] em;
	private Intent intent;
	boolean primeravez = true;

	/*
	 * opciones que mostrara nuestro listView
	 */
	public String[] nombres = { "Reproducir Música", "Reproducir Vídeo",
			" Galería de Imágenes", "Grabar Sonido", "Grabar Vídeo", "Cámara" };
	public int nun = 0;

	// constante que identifica el tipo de dialogo a mostrar:

	private static final int DIALOGO_ALERTA = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multimedia);

		list = (ListView) findViewById(R.id.listView1);

		em = cargarNews();
		MyAdapterProyect adapter = new MyAdapterProyect(this, R.layout.segundo,
				em);
		list.setAdapter(adapter);
		/**
		 * sistema de lectura de frases (salida de texto a voz), que pasa un
		 * texto escrito, a un formato de audio
		 */
		voz = new TextToSpeech(this, this);

		// para mandarlo para las otra main

		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Opcion obj = (Opcion) arg0.getItemAtPosition(arg2);
				nun = arg2;

				clases(nun);

			}

		});
	}

	/**
	 * 
	 * Dependiendo del el numero entero que se le pase sera la clase que se
	 * ejecutara
	 */
	public void clases(int nun) {

		switch (nun) {

		case 0:
			intent = new Intent("com.example.listviewopcionesproyect.RepMusic");
			break;
		case 1:
			intent = new Intent(
					"com.example.listviewopcionesproyect.ActiviVideo");
			break;
		case 2:
			intent = new Intent(
					"com.example.listviewopcionesproyect.ActivyGaleria");
			break;
		case 3:
			intent = new Intent("com.example.listviewopcionesproyect.Grabador");
			break;
		case 4:
			intent = new Intent(
					"com.example.listviewopcionesproyect.GrabarVideo");
			break;
		case 5:
			intent = new Intent(
					"com.example.listviewopcionesproyect.ActivCamara");

			break;

		}

		startActivity(intent);

		onInit(nun);

	}

	/**
	 * para cargar el listView
	 */
	private Opcion[] cargarNews() {
		Opcion[] n = new Opcion[nombres.length];
		for (int i = 0; i < n.length; i++) {
			n[i] = new Opcion(nombres[i], i);
		}

		return n;
	}

	@Override
	// este es para inflar el menu1 que es el menu principal
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu1, menu);
		return true;
	}

	/**
	 * segundo acerca de ... te manda a la documentación de android y te muestra
	 * el nombre de los programadores , tercero cierra la aplicación
	 * 
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case R.id.item2:
			break;
		case R.id.item3:
			showDialog(DIALOGO_ALERTA);
			crearDialogoAlerta();

			break;

		case R.id.item4:
			crearDatos();
			break;

		case R.id.item5:
			i = new Intent("android.intent.action.VIEW",
					Uri.parse("http://developer.android.com"));
			startActivity(i);
			break;
		}
		return true;
	}

	/**
	 * Datos de los programadores del proyecto se mostrara en un toast
	 * personalizado
	 * 
	 */

	private void crearDatos() {
		Toast toast3 = new Toast(getApplicationContext());

		LayoutInflater inflater = getLayoutInflater();
		// aqui le pongo la segunda acti.xml y el liner layout
		View layout = inflater.inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.linerLayout));

		toast3.setDuration(Toast.LENGTH_LONG);
		toast3.setView(layout);
		toast3.show();

	}

	// este metodo es para crear la clase dialogo
	protected Dialog onCreateDialog(int id) {
		Dialog dialogo = null;

		switch (id) {

		case DIALOGO_ALERTA:
			dialogo = crearDialogoAlerta();
			break;

		default:
			dialogo = null;
			break;
		}

		return dialogo;
	}

	// aqui saltara la alerta para que el usuario sepa que a dado a finalizar y lo leera en voz alta

	private Dialog crearDialogoAlerta() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Información");// TITULO DE LA VENTANA
		builder.setMessage("Alerta finalizara la aplicación.");// MENSAJE A
																// SALIR
		voz.speak("Alerta finalizará la aplicación", TextToSpeech.QUEUE_FLUSH,
				null);
		builder.setPositiveButton("ok", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				finish();// cuando se le de al ok finalizara la aplicación
			}
		});
		return builder.create();

	}

	/**
	 * a este método se le pasa un entero que sera de la posición del list donde
	 * alla se le halla dado el click y esto lee lo que se encuentre en el array
	 * en esa posición
	 * 
	 */

	public void onInit(int nun) {

		if (primeravez) {
			voz.speak("bienvenido al proyecto multimedia",
					TextToSpeech.QUEUE_FLUSH, null);
			primeravez = false;
		} else {
			voz.speak(nombres[nun], TextToSpeech.QUEUE_FLUSH, null);
		}

	}

}
