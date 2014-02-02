package com.datohosting.ejemploactionbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

public class CrearTabs extends ActionBarActivity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("6.Crear tabs");
		
		/**MOSTRAR BOTON ATRAS EN ACTION BAR**/
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		/**MODO TABS EN ACTIONBAR**/
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    /**CREAR TABS**/
	    Tab tab = actionBar.newTab()
	                       	.setText(R.string.productos)
	                       	.setTabListener(new TabsListener<Fragment_Productos>(
	                               this, "productos", Fragment_Productos.class));
	    actionBar.addTab(tab);

	    tab = actionBar.newTab()
	                   	.setText(R.string.clientes)
	                   	.setTabListener(new TabsListener<Fragment_Clientes>(
	                           this, "clientes", Fragment_Clientes.class));
	    actionBar.addTab(tab);
	    
	    tab = actionBar.newTab()
                	    .setText(R.string.pedidos)
                	    .setTabListener(new TabsListener<Fragment_Pedidos>(
                	    		this, "pedidos", Fragment_Pedidos.class));
	    actionBar.addTab(tab);
	}
	
	
	
	
	public class TabsListener <T extends Fragment> implements ActionBar.TabListener {
		
		private Fragment fragment;
	    private final String tag;
	    
	    public TabsListener(Activity activity, String tag, Class<T> cls) {
	        this.tag = tag;
	        fragment = Fragment.instantiate(activity, cls.getName());
	    }
	    
	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	    	ft.replace(android.R.id.content, fragment, tag);
	    }
	    
	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	    	ft.remove(fragment);
	    }
	    
	    public void onTabReselected(Tab tab, FragmentTransaction ft) {}
	    
	}
	
	
	
}