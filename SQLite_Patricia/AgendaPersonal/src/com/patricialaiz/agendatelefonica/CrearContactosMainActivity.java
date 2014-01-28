package com.patricialaiz.agendatelefonica;


import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 
 * @author Patricia Laiz
 *
 */
public class CrearContactosMainActivity extends Activity {
	
	private EditText nombre, telefono, email, notas;
	private BaseDatosPersonas databasePersonas;
	//creamos una variable SQLiteDatabase para poder manejar nuestra base de datos
	private SQLiteDatabase SQLitedb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_contactos_main);
		
		nombre = (EditText) findViewById(R.id.eTNom);
		telefono = (EditText) findViewById(R.id.eTTel);
		email = (EditText) findViewById(R.id.eTEmail);
		notas = (EditText) findViewById(R.id.eTNotas);
		
		// Creamos la base de datos de personas y la abrimos para leer y escribir
		databasePersonas = new BaseDatosPersonas(this, "personas.db", null, 1);
		SQLitedb = databasePersonas.getWritableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_contactos_main, menu);
		return true;
	}
	/**
	 * 
	 * Metodo al que le pasamos los valores y nos va guardando los registros en la base de datos
	 */
	public void insertarContacto(View v){
		String nomTemp = nombre.getText().toString();
		int telTemp = 0;
		telTemp = Integer.parseInt(telefono.getText().toString());
		String emailTemp = email.getText().toString();
		String notasTemp = notas.getText().toString();
		
		if(SQLitedb != null){
			// utilizamos un contentValues como contenedor de los datos a meter en la base de datos
	        ContentValues valores = new ContentValues();
	        //metemos los datos en nuestro contenedor
	        valores.put("nombre", nomTemp);
	        valores.put("telefono", telTemp);
	        valores.put("email", emailTemp);
	        valores.put("notas", notasTemp);  
	        //insertamos los datos en la base
	        SQLitedb.insert("Personas", null, valores);
	        Toast.makeText(this, "Contacto creado", Toast.LENGTH_SHORT).show();
	      //vaciamos los datos al guardar cada registro
			vaciarDatos();
	    } else {
			Toast.makeText(this, "Tiene que introducir los datos", Toast.LENGTH_SHORT).show();
	    }
		
	}
	/**
	 * metodo para vaciar los editText
	 */
	public void vaciarDatos(){
		nombre.setText("");
		telefono.setText("");
		email.setText("");
		notas.setText("");
	}

	public void volver(View v) {
		SQLitedb.close();
		finish();
	}
}
