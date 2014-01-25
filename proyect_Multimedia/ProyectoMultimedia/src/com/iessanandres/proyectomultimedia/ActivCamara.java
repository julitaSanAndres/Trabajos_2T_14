package com.iessanandres.proyectomultimedia;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivCamara extends Activity implements
		TextToSpeech.OnInitListener {

	private ImageView iv;
	private String name = "";// para la ruta
	public Intent intent;
	public Uri output = null;
	public final int code = 1;
	private TextToSpeech voz;
	private int num = 0;

	/**
	 * 
	 * SimpleDateFormat clase concreta para dar formato a fechas y analizar de
	 * una manera sensible a la localidad. Formateo convierte una fecha en una
	 * cadena esto lo utilizaremos para dar nombre a la foto que queremos
	 * guardar.
	 */
	String fe = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

	/**
	 * Environment-Proporciona acceso a las variables de entorno.
	 * setExternalStorageDirectory()-Obtiene el directorio Android de
	 * almacenamiento externo Este directorio puede ser pensado como medios de
	 * almacenamiento compartido. Se trata de un sistema de archivos que puede
	 * contener una cantidad relativamente grande de datos y que se comparte
	 * entre todas las aplicaciones. Normalmente, se ve como tarjeta SD, pero
	 * también puede ser implementado como almacenamiento integrado en el
	 * dispositivo .
	 * 
	 * bueno en otras palabras esto en nuestro programa la utilizamos para
	 * ponerle la ruta donde guardaremos la imágen.
	 * 
	 * no se utiliza ningún layaot porque las imagenes se podra ver con los
	 * medios del dispositivo movil.
	 * 
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camara);
		// para el nombre de la imágen a guardar
		name = Environment.getExternalStorageDirectory() + "/test" + fe
				+ ".jpg";

		iv = (ImageView) findViewById(R.id.imageView1);

		// voz inicialización del objeto
		voz = new TextToSpeech(this, this);

		camara();

	}

	/**
	 * aqui tendremos declarado e inicializados la uri donde se encontrara la
	 * imagen y el intent para ejecutar la cámara del android
	 */
	private void camara() {
		// aqui se pasara a la camara directamente

		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		output = Uri.fromFile(new File(name));// guardar como tipo de
												// archivo

		intent.putExtra(MediaStore.EXTRA_OUTPUT, output);

		startActivityForResult(intent, code);

	}

	/**
	 * ete método es para ver y guardar en la galaria la imagen tomada por la
	 * cámara
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// iv.setImageBitmap(BitmapFactory.decodeFile(name));// problema que
		// tiene es que enseña la foto anterior

		/**
		 * Para guardar la imagen en la galería, utilizamos una conexión a un
		 * MediaScanner
		 */

		if (resultCode == RESULT_CANCELED) {
			num = 1;
			onInit(num);
			finish();
			// si se le da a cancel se saldra a la actvi principal
		} else {
			new MediaScannerConnectionClient() {
				private MediaScannerConnection msc = null;
				{
					msc = new MediaScannerConnection(getApplicationContext(),
							this);
					msc.connect();
				}

				// notifica la llamada de conexion con MediaScanner
				public void onMediaScannerConnected() {
					msc.scanFile(name, null);
				}

				/**
				 * notifica al usuario cuando el escáner terminado de escanear
				 * un archivo. Parámetros a pasarle ruta de acceso al archivo
				 * que se ha escaneado. el URI para el archivo si la operación
				 * tuvo éxito y el escaneo de archivos se añaden a la base de
				 * datos de medios de comunicación y después desconecta
				 */

				public void onScanCompleted(String path, Uri uri) {
					msc.disconnect();
				}
			};
			num = 2;
			onInit(num);
			iv.setImageBitmap(BitmapFactory.decodeFile(name));

		}

	}

	public void onInit(int num) {
		if (num == 1) {
			voz.speak("Cámara cancelada", TextToSpeech.QUEUE_FLUSH, null);

		} else if (num > 1) {
			voz.speak("foto aceptada", TextToSpeech.QUEUE_FLUSH, null);
		}

	}

}
