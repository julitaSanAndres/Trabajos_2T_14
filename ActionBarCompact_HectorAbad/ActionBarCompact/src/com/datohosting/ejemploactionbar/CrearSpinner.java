package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.datohosting.ejemploactionbar.R;

public class CrearSpinner extends ActionBarActivity implements ActionBar.OnNavigationListener{
	
	
	TextView texto;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		texto = (TextView) findViewById(R.id.texto);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("9.Añadir spinner");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		/**MOSTRAR SPINNER**/
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
        /**CREAR SPINNER**/
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.lista, android.R.layout.simple_spinner_dropdown_item);
		actionBar.setListNavigationCallbacks(adapter, this);
	}
	
	
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		switch (arg0) {
			case 0:
				texto.setText("Opcion seleccionada:\n\nLunes");
				break;
			case 1:
				texto.setText("Opcion seleccionada:\n\nMartes");
				break;
			case 2:
				texto.setText("Opcion seleccionada:\n\nMiercoles");
				break;
			case 3:
				texto.setText("Opcion seleccionada:\n\nJueves");
				break;
			case 4:
				texto.setText("Opcion seleccionada:\n\nViernes");
				break;
			case 5:
				texto.setText("Opcion seleccionada:\n\nSabado");
				break;
			case 6:
				texto.setText("Opcion seleccionada:\n\nDomingo");
				break;
		}
		return false;
	}
	
	
}