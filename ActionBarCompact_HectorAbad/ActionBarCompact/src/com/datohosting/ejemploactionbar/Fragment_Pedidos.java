package com.datohosting.ejemploactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Pedidos extends Fragment {
	
	View rootView;
	int uno = 0;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.fm_pedidos, container, false);
		
		TextView texto = (TextView) rootView.findViewById(R.id.texto_pedidos);
		
		texto.setText("Tab seleccionada" + "\n\n" + "Pedidos" + "\n\n" + uno);
		
		uno++;
		
		return rootView;
	}
	
	
}