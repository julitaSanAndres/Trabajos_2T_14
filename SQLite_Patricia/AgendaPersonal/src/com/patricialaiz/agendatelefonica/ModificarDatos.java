package com.patricialaiz.agendatelefonica;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarDatos extends Activity {
	private EditText modNom, modTel, modEmail, modNotas;
	private BaseDatosPersonas databasePersonas;
	// creamos una variable SQLiteDatabase para poder manejar nuestra base de
	// datos
	private SQLiteDatabase SQLitedb;
	private int idAmodificar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_datos);
		modNom = (EditText) findViewById(R.id.editTextNombre);
		modTel = (EditText) findViewById(R.id.editTextTelefono);
		modEmail = (EditText) findViewById(R.id.editTextEmail);
		modNotas = (EditText) findViewById(R.id.editTextNotas);

		// Creamos la base de datos de personas y la abrimos para leer y escribir
		databasePersonas = new BaseDatosPersonas(this, "personas.db", null, 1);
		SQLitedb = databasePersonas.getWritableDatabase();

		Bundle bundle = getIntent().getExtras();
		idAmodificar = bundle.getInt("id");
		modNom.setText(bundle.getString("nombre"));
		modTel.setText(String.valueOf(bundle.getInt("telefono")));
		modEmail.setText(bundle.getString("email"));
		modNotas.setText(bundle.getString("notas"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modificar_datos, menu);
		return true;
	}

	public void guardarCambiosContacto(View v) {
		String nomTemp = modNom.getText().toString();
		int telTemp = 0;
		telTemp = Integer.parseInt(modTel.getText().toString());
		String emailTemp = modEmail.getText().toString();
		String notasTemp = modNotas.getText().toString();

		if (SQLitedb != null) {
			// utilizamos un contentValues como contenedor de los datos a meter
			// en la base de datos
			ContentValues valores = new ContentValues();
			// metemos los datos en nuestro contenedor
			valores.put("nombre", nomTemp);
			valores.put("telefono", telTemp);
			valores.put("email", emailTemp);
			valores.put("notas", notasTemp);

			String idmodi = "id == " + idAmodificar;
			// Sentencia para modificar los valores
			SQLitedb.update("Personas", valores, idmodi, null);
			Toast.makeText(this, "Se guardaron los cambios", Toast.LENGTH_SHORT)
			.show();
			volver();
			
		}

	}

	public void volver() {
		finish();
	}
}
