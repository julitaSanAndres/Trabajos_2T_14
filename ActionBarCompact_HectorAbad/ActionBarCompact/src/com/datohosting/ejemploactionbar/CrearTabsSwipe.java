package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

public class CrearTabsSwipe extends ActionBarActivity implements ActionBar.TabListener, OnPageChangeListener {

	private ViewPager mViewPager;;
	
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.tabs_swipe);
		
		PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        
        mViewPager.setOnPageChangeListener(this);
        
        ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("7.Crear tabs swipe");
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab tab = actionBar.newTab().setText(R.string.productos).setTabListener(this);
		actionBar.addTab(tab);

		tab = actionBar.newTab().setText(R.string.clientes).setTabListener(this);
		actionBar.addTab(tab);

		tab = actionBar.newTab().setText(R.string.pedidos).setTabListener(this);
		actionBar.addTab(tab);
		
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

    }






	/** INTERFACE ONPAGECHANGELISTENER **/
	
	public void onPageSelected(int arg0) {
		getSupportActionBar().setSelectedNavigationItem(arg0);
	}
	
	public void onPageScrollStateChanged(int arg0) {
		
	}
	
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	
	
	
	
	
	/** INTERFACE ACTIONBAR.TABLISTENER **/
	
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		mViewPager.setCurrentItem(arg0.getPosition());
	}
	
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		
	}
	
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		
	}
	
	
	
	
	
}