package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.datohosting.ejemploactionbar.R;

public class PanelDesplegable extends ActionBarActivity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("5.Panel desplegable");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	}

	/** METODO 1 **/
	// Boton ShareActionProvider
	
	/*
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.paneldesplegable, menu);
	    MenuItem shareItem = menu.findItem(R.id.menu4_compartir);
	    ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
	    
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("image/*");
	    mShareActionProvider.setShareIntent(intent);

	    return super.onCreateOptionsMenu(menu);
	}
	*/
	
	
	
	
	
	/** METODO 2 **/
	// Boton Personalizado
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.busqueda_ap, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	
	
	
}