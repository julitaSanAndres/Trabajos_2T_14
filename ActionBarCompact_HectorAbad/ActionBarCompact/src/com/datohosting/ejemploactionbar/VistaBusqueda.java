package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.datohosting.ejemploactionbar.R;

public class VistaBusqueda extends ActionBarActivity implements OnQueryTextListener, OnActionExpandListener {
	
	
	private TextView texto;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("4.Vista Busqueda");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		texto = (TextView) findViewById(R.id.texto);
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.vistabusqueda, menu);
	    
	    MenuItem searchItem = menu.findItem(R.id.menu3_buscar);

	    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
	    searchView.setOnQueryTextListener(this);
	    
	    MenuItemCompat.setOnActionExpandListener(searchItem, this);
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	
	
	
	public boolean onQueryTextChange(String arg0) {
		texto.setText("Escribiendo texto...\n\n" + arg0);
		return false;
	}
	
	public boolean onQueryTextSubmit(String arg0) {
		texto.setText("Texto a buscar\n\n" + arg0);
		return false;
	}
	
	
	
	
	
	public boolean onMenuItemActionCollapse(MenuItem arg0) {
		Toast.makeText(getApplicationContext(), "COLLAPSE", Toast.LENGTH_SHORT).show();
		return true;
	}
	
	public boolean onMenuItemActionExpand(MenuItem arg0) {
		Toast.makeText(getApplicationContext(), "EXPAND", Toast.LENGTH_SHORT).show();
		return true;
	}
	
	
	
	
}