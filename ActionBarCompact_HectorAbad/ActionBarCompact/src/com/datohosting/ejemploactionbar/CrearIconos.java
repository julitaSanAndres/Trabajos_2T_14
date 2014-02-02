package com.datohosting.ejemploactionbar;

import com.datohosting.ejemploactionbar.R;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class CrearIconos extends ActionBarActivity {
	
	
/**
 * Poner la activity padre en el AndroidManifest
 *  para controlar el boton atras de la action bar
 */
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("2.Crear iconos");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		 
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.iconos, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu2_buscar:
	        	Toast.makeText(getApplicationContext(), "BUSCAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu2_cortar:
	        	Toast.makeText(getApplicationContext(), "CORTAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu2_copiar:
	        	Toast.makeText(getApplicationContext(), "COPIAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu2_eliminar:
	        	Toast.makeText(getApplicationContext(), "ELIMINAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu2_compartir:
	        	Toast.makeText(getApplicationContext(), "COMPARTIR", Toast.LENGTH_SHORT).show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
}