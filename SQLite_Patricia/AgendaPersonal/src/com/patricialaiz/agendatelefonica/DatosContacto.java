package com.patricialaiz.agendatelefonica;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DatosContacto extends Activity {
	
	private TextView dnombre, dtelefono, demail, dnotas;
	private Persona contacto;
	private Button borrar;
	private BaseDatosPersonas databasePersonas;
	// creamos una variable SQLiteDatabase para poder manejar nuestra base de
	// datos
	private SQLiteDatabase SQLitedb;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datos_contacto);
		
		dnombre = (TextView) findViewById(R.id.tvdetalleNom);
		dtelefono = (TextView) findViewById(R.id.tvDetalleTel);
		demail = (TextView) findViewById(R.id.tvDEtaleEmail);
		dnotas = (TextView) findViewById(R.id.tvdetalleNotas);
		borrar = (Button) findViewById(R.id.buttonBorrar);
		
		// Crea base de datos de libros y la abre con propiedades de lectura y
				// escritura
		databasePersonas = new BaseDatosPersonas(this, "basedatospersonas.db", null, 1);
		SQLitedb = databasePersonas.getWritableDatabase();
		
		Bundle bundle = getIntent().getExtras();
		//recogemos el objeto persona del que sacaremos la informacion
		contacto = (Persona) getIntent().getSerializableExtra("obj");
		
		dnombre.setText(contacto.getNombre());
		dtelefono.setText(String.valueOf(contacto.getTelefono()));
		demail.setText(contacto.getEmail());
		dnotas.setText(contacto.getNotas());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.datos_contacto, menu);
		return true;
	}
	/**
	 * metodo para eliminar contactos
	 */
	public void borrar(View v){
		String idAeliminar = "id == " + contacto.getId();
		//Sentencia para eliminar el objeto nombre de la tabla, registro a borrar (usamos el id) y valores a borrar
		SQLitedb.delete("Personas", idAeliminar, null);
	//	Intent intent = new Intent(this, ListadoPersonas.class);
		Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
		finish();
	}
	/**
	 * 
	 * metodo que nos lleva a la pantalla para modificar datos 
	 * pasandole los datos originales
	 */
	public void modificarContacto(View v){

		Intent intent = new Intent(this, ModificarDatos.class );
		intent.putExtra("id", contacto.getId());
		intent.putExtra("nombre", contacto.getNombre());
		intent.putExtra("telefono", contacto.getTelefono());
		intent.putExtra("email", contacto.getEmail());
		intent.putExtra("notas", contacto.getNotas());
		startActivity(intent);
		//para que se vaya a la lista directamente cuando finalicemos las modificaciones del contacto
		finish();
	}

	public void volver(View v) {
		finish();
	}
}
