package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.datohosting.ejemploactionbar.R;

public class AplicarEstilo extends ActionBarActivity implements ActionBar.TabListener {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texto);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("13.Aplicar estilor");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		/**MOSTRAR TABS**/
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
		/**CREAR TABS**/
        Tab tab = actionBar.newTab().setText("TAB 1").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("TAB 2").setTabListener(this);
        actionBar.addTab(tab);
        
        tab = actionBar.newTab().setIcon(R.drawable.ic_action_share).setTabListener(this);
        actionBar.addTab(tab);
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.iconos, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {}
	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {}
	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {}
	
	
}