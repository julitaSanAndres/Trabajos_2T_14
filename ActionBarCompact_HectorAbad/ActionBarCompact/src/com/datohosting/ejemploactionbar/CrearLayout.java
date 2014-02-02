package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class CrearLayout extends ActionBarActivity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("12.Crear layout");
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
	    actionBar.setCustomView(R.layout.actionbar_top);
	    
	}
	
	
	
	
}