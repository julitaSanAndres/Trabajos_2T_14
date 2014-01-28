package com.patricialaiz.agendatelefonica;

import java.io.Serializable;
import java.util.Arrays;

import android.net.MailTo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListadoPersonas extends Activity implements Serializable {

	private ListView lista;
	private BaseDatosPersonas databasePersonas;
	// creamos una variable SQLiteDatabase para poder manejar nuestra base de
	// datos
	private SQLiteDatabase SQLitedb;
	private Persona[] listaContatcos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_personas);
		lista = (ListView) findViewById(R.id.listView1);
		Bundle bundle = getIntent().getExtras();

		// Creamos base de datos y la abrimos en modo solo lectura
	
		databasePersonas = new BaseDatosPersonas(this, "Libros.db", null, 1);
		SQLitedb = databasePersonas.getReadableDatabase();


		// registra el contexto que sera mostrado en nuestra actividad
		registerForContextMenu(lista);
		// cargamos el listView con los elementos
		cargarLista();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listado_personas, menu);
		return true;
	}

	/**
	 * Este metodo recoge los objetos persona de la base datos y muestra los
	 * datos en un listView
	 */
	private void cargarLista() {

		// Comprueba si la base da datos existe
		if (SQLitedb != null) {
			int id = 0;
			String nombre = null;
			int telefono = 0;
			String email = null;
			String notas = null;

			// realizamos la consulta a la base de datos y la guardamos en un
			// cursor
			Cursor c = SQLitedb.rawQuery("SELECT * FROM Personas", null);

			// mueve el cursor a la primera fila, leemos la base de datos por
			// filas
			if (c.moveToFirst()) {

				// Crea un array de personas con el tamaño del numero de
				// elementos que hay en la base de datos
				listaContatcos = new Persona[c.getCount()];
				int i = 0;
				do {
					// Sacamos la informacion de cada columna de un elemento
					// La columna de 0 es el id
					id = c.getInt(0);
					// La columna de 1 es el nombre
					nombre = c.getString(1);
					// La columna 2 es el telefono
					telefono = Integer.parseInt(c.getString(2).toString());
					// La columna 3 es el email
					email = c.getString(3);
					// La columna de 4 son las notas
					notas = c.getString(4);

					// Llenamos el array con los contactos
					listaContatcos[i] = new Persona(id, nombre, telefono,
							email, notas);
					
					i++;
					// movemos el cursor al siguiente registro
				} while (c.moveToNext());
				
				// Creamos el adaptador
				MyAdapterContactos adapter = new MyAdapterContactos(this,
						R.layout.myadapter_contactos, listaContatcos);

				lista.setAdapter(adapter);
				// cuando pulsamos en uno de los contactos se nos abre otra
				// pantalla con los datos de ese contacto
				lista.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> adap,
							View convertView, int position, long id) {
						Persona obj = (Persona) adap
								.getItemAtPosition(position);
						item(listaContatcos[position]);
					}
				});
			} else {
				// sino hay ningun contacto creado mostrara un mensaje
				//listaContatcos = new Persona[0];
		//		listaContatcos = null;
//				MyAdapterContactos adaptador = new MyAdapterContactos(this,
//						R.layout.myadapter_contactos, listaContatcos);
				Toast.makeText(this, "No hay contactos", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	public void volver(View v) {
		finish();
	}

	/**
	 * Inicia la Activity DatosContacto, pasandole el objeto del que queremos
	 * saber
	 * 
	 */
	public void item(Persona obj) {
		Intent intent = new Intent(this, DatosContacto.class);
		intent.putExtra("obj", obj);
		startActivity(intent);
		finish();
	}

	/**
	 * Al volver a lanzar esta actividad ejecuta el método cargarLista()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		cargarLista();
	}

}
