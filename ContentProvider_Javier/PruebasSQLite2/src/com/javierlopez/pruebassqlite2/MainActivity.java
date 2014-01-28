package com.javierlopez.pruebassqlite2;

import com.javierlopez.pruebassqlite2.ContentProviderCliente.Productos;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button bConsultar, bInsertar, bBorrar;
	private TextView txtResultados;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtResultados = (TextView) findViewById(R.id.tVDatos);
		bConsultar = (Button) findViewById(R.id.bConsulta);
		bInsertar = (Button) findViewById(R.id.bInsertar);
		bBorrar = (Button) findViewById(R.id.bBorrar);

	}

	public void consultar(View v) {
		// Columnas de la tabla a recuperar
		String[] projection = new String[] { Productos._ID, Productos.COL_NOMBRE,
				Productos.COL_PRECIO };
		Uri clientesUri = ContentProviderCliente.CONTENT_URI;
		ContentResolver cr = getContentResolver();

		// Hacemos la consulta
		Cursor cur = cr.query(clientesUri, projection, // Columnas a devolver
				null, // Condición de la query
				null, // Argumentos variables de la query
				null); // Orden de los resultados

		if (cur.moveToFirst()) {
			String nombre;
			String precio;
			int colNombre = cur.getColumnIndex(Productos.COL_NOMBRE);
			int colPrecio = cur.getColumnIndex(Productos.COL_PRECIO);
			txtResultados.setText("");
			do {
				nombre = cur.getString(colNombre);
				precio = cur.getString(colPrecio);
				txtResultados.append(nombre + " - " + precio + "\n");
			} while (cur.moveToNext());
		}
	}

	public void insertar(View v) {
		ContentValues values = new ContentValues();
		
		values.put(Productos.COL_NOMBRE, "ProductoN");
		values.put(Productos.COL_PRECIO, "10000");
		
		ContentResolver cr = getContentResolver();
		
		cr.insert(ContentProviderCliente.CONTENT_URI, values);
	}

	public void borrar(View v) {
		String[] campos = {"ProductoN"};
		ContentResolver cr = getContentResolver();
		
		//El simbolo de ? indica que en esa posicion ira el primer valor del array campos
		//se podrian hacer sentencias mas complejas añadiendo AND para crear wheres mas 
		//grandes
		cr.delete(ContentProviderCliente.CONTENT_URI,
		Productos.COL_NOMBRE + "=?", campos);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
