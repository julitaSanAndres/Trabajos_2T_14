package com.datohosting.ejemploactionbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.datohosting.ejemploactionbar.R;

public class BotonActualizar extends ActionBarActivity {
	
	
	private boolean actualiza;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("11.Boton actualizar");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.actualizar, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    	case R.id.actualizar:
	    		if (!actualiza) {
	    			setContentView(R.layout.actualizar);
		    		actualiza = true;
		    		new Actualizar().execute();
				}
	    		break;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	
	private class Actualizar extends AsyncTask<String, Void, String> {
		
		protected String doInBackground(String... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
	    }

	    protected void onPostExecute(String result) {
	    	setContentView(R.layout.texto);
	    	actualiza = false;
	    }
	};
	
	
}