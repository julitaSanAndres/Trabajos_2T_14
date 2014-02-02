package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class CrearTabsSwipe2 extends ActionBarActivity {
	
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.tabs_swipe_dos);
		
		PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
		ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        
        ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("8.Crear tabs swipe 2");
		
	}
	public class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int arg0) {
			switch (arg0) {
	            case 0:
	                return new Fragment_Productos();
	            case 1:
	                return new Fragment_Clientes();
	            case 2:
	                return new Fragment_Pedidos();
	            default:
	            	return null;
			}
		}

		public int getCount() {
			return 3;
		}
		
		public CharSequence getPageTitle(int position) {
			String titulo = null;
			switch (position) {
				case 0:
	                titulo = "PRODUCTOS";
	                break;
	            case 1:
	            	titulo = "CLIENTES";
	                break;
	            case 2:
	            	titulo = "PEDIDOS";
	                break;
			}
			return titulo;
		}

    }
	
	
	
	
	
}