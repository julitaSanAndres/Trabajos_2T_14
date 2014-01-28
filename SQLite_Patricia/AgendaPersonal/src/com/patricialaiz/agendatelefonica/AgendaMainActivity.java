package com.patricialaiz.agendatelefonica;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
/**
 * 
 * @author Patricia Laiz
 * 
 *Desde aqui daremos paso a ver (si hay datos) los contactos, o a crear contactos
 */
public class AgendaMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agenda_main, menu);
		return true;
	}
	/**
	 * 
	 * metodo que nos mostrara en un listview todos los contactos que tenemos
	 */
	public void mostrarContactos(View v){
		Intent intent = new Intent(this, ListadoPersonas.class );
		  startActivity(intent);
	}
	/**
	 * 
	 * metodo que nos data paso a la pantalla donde podremos ir metiendo los datos 
	 * para ir crando contactos nuevos
	 */
	public void AnadirContactos(View v){
		Intent intent = new Intent(this, CrearContactosMainActivity.class );
		  startActivity(intent);
		
	}

}
