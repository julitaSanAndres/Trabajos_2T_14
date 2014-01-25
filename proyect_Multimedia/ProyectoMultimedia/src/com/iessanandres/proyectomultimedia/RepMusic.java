package com.iessanandres.proyectomultimedia;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class RepMusic extends ListActivity {

	// Filas del la lista
	SimpleCursorAdapter fila;
	// Ruta de las diferentes sonidos internos del dispositivo
	final Uri rutaAudio = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// coger los títulos de los sonidos
		String[] titulos = { MediaStore.MediaColumns.TITLE };

		// número total de sonidos que tiene el dispositivo
		int[] to = { android.R.id.text1 };

		// cursor que guarda, la ruta del sonido, un intent, una selección, y
		// los títulos.
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(rutaAudio, null, null, null,
				MediaStore.Audio.Media.TITLE);

		// y la fila se carga en esta ventana, con lo que contenga el cursor,
		// los títulos y el número de sonidos q tenga el dispositivo
		fila = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, titulos, to);

		// se carga la fila del adaptador
		setListAdapter(fila);
	}

	// Selección del sonido de la lista
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		// Se crea un cursor que coge la fila
		Cursor cursor = fila.getCursor();

		// Y se coge la posición del video marcado
		cursor.moveToPosition(position);

		// Se guarda el numero de la fila escogida
		String num = cursor.getString(cursor
				.getColumnIndex(MediaStore.Audio.Media._ID));

		// Se guarda la ruta del sonido, con la ruta escogida y el numero
		// seleccionado
		Uri playableUri = Uri.withAppendedPath(rutaAudio, num);

		// Se muestra un mensaje con la ruta del sonido escogido
		Toast.makeText(this, "Play: " + playableUri.toString(),
				Toast.LENGTH_SHORT).show();

		// Se abre en una ventana nueva, para reproducir el sonido
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(playableUri);
		startActivity(intent);

	}

}
