package com.datohosting.ejemploactionbar;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;

import com.datohosting.ejemploactionbar.R;

public class BusquedaAP extends ActionProvider{
	 
    Context context;
    
    public BusquedaAP(Context context) {
        super(context);
        this.context = context;
    }
 
    public View onCreateActionView() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ap_busqueda, null);
        return view;
    }
}