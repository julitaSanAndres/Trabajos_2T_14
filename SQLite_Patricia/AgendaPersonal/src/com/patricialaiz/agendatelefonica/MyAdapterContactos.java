package com.patricialaiz.agendatelefonica;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapterContactos  extends ArrayAdapter<Persona> {
	private Activity context;
	private Persona[] datos;
	private int viewResourceId;
	
	public MyAdapterContactos(Activity context, int textViewResourceId, Persona[] p) {
		super(context, textViewResourceId, p);
		this.context = context;
		this.datos = p;
		this.viewResourceId = textViewResourceId;
	} 
	

	public View getView(int position, View convertView, ViewGroup parent) {

		View item = convertView;// el view de una fila puede haberse creado antes o no
		ViewHolder holder; // recipiente de views

		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(viewResourceId, null);
			holder = new ViewHolder();
			
			holder.nombre = (TextView) item.findViewById(R.id.tvnombre);//nombre
			holder.telefono= (TextView) item.findViewById(R.id.tvtelefono);//telefono
			item.setTag(holder);// metodos para los componentes view, si se
								// necesita añadir informacion extra
		} else {

			holder = (ViewHolder) item.getTag();
		}
		
		holder.nombre.setText(datos[position].getNombre());//mostramos el el nombre 
		holder.telefono.setText(String.valueOf(datos[position].getTelefono()));//mostramos el telefono
		
		return (item);
	}
	
	static class ViewHolder {
		protected TextView nombre;
		protected TextView telefono;
	}
}
