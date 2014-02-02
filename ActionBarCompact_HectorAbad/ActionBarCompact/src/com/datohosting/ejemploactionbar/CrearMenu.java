package com.datohosting.ejemploactionbar;

import com.datohosting.ejemploactionbar.R;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class CrearMenu extends ActionBarActivity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("1.Crear menu");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		TextView texto = (TextView) findViewById(R.id.texto);
		texto.setText("Pulsa la tecla menu del smartphone\npara ver el resultado");
		 
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu1_buscar:
	        	Toast.makeText(getApplicationContext(), "BUSCAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu1_cortar:
	        	Toast.makeText(getApplicationContext(), "CORTAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu1_copiar:
	        	Toast.makeText(getApplicationContext(), "COPIAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu1_eliminar:
	        	Toast.makeText(getApplicationContext(), "ELIMINAR", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.menu1_compartir:
	        	Toast.makeText(getApplicationContext(), "COMPARTIR", Toast.LENGTH_SHORT).show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
}