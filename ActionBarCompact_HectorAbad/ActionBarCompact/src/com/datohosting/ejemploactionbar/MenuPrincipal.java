package com.datohosting.ejemploactionbar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MenuPrincipal extends ActionBarActivity implements OnItemClickListener {
	
	
/**
 *  Aplicamos el tema -- android:theme="@style/Theme.AppCompat.Light.DarkActionBar"
 *  en el AndroidManifest a la activity que use ActionBar
 */
	
	
	ArrayList<ObjetoEntradas> items = new ArrayList<ObjetoEntradas>();
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_principal);
		
		
		ActionBar actionBar = getSupportActionBar();
		
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("EJ ActionBar");
		actionBar.setSubtitle("Menu Principal");
        
		crearEntradas();  
	    EntradasAdapter adapter = new EntradasAdapter(this, items);
	    
	    ListView lv = (ListView)findViewById(R.id.list);
	    lv.setAdapter(adapter);   
	    lv.setOnItemClickListener(this);
	}
	
	
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
	
	
	private void crearEntradas() {
	    items.add(new ObjetoEntradas(1, "1. Crear menu", "Como agregar elementos de accion"));
	    items.add(new ObjetoEntradas(2, "2. Crear iconos", "Como hacer visibles los elementos de accion"));
	    items.add(new ObjetoEntradas(3, "3. Dividir ActionBar", "Como dividir los elementos de la ActionBar"));
	    items.add(new ObjetoEntradas(4, "4. Vista Busqueda", "Como crear la barra de busqueda"));
	    items.add(new ObjetoEntradas(5, "5. Vista de accion", "Como crear una accion desplegable"));
	    items.add(new ObjetoEntradas(6, "6. Crear tabs", "Como crear una barra con pestañas"));
	    items.add(new ObjetoEntradas(7, "7. Crear tabs swipe", "Crear pestañas deslizantes"));
	    items.add(new ObjetoEntradas(8, "8. Crear tabs swipe2", "Crear pestañas deslizantes"));
	    items.add(new ObjetoEntradas(9, "9. Añadir spinner", "Como añadir una lista desplegable"));
	    items.add(new ObjetoEntradas(10, "10. Crear cajon", "Como crear un cajon de navegacion"));
	    items.add(new ObjetoEntradas(11, "11. Boton actualizar", "Como crear un boton actualizar"));
	    items.add(new ObjetoEntradas(12, "12. Crear layout", "Como aplicar un layout personalizado a la ActionBar"));
	    items.add(new ObjetoEntradas(13, "13. Aplicar estilo", "Como aplicar estilo a la ActionBar"));
	}
	
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
			case 0:
				startActivity(new Intent(this, CrearMenu.class));
				break;
			case 1:
				startActivity(new Intent(this, CrearIconos.class));
				break;
			case 2:
				startActivity(new Intent(this, DividirActionBar.class));
				break;
			case 3:
				startActivity(new Intent(this, VistaBusqueda.class));
				break;
			case 4:
				startActivity(new Intent(this, PanelDesplegable.class));
				break;
			case 5:
				startActivity(new Intent(this, CrearTabs.class));
				break;
			case 6:
				startActivity(new Intent(this, CrearTabsSwipe.class));
				break;
			case 7:
				startActivity(new Intent(this, CrearTabsSwipe2.class));
				break;
			case 8:
				startActivity(new Intent(this, CrearSpinner.class));
				break;
			case 9:
				startActivity(new Intent(this, CrearCajon.class));
				break;
			case 10:
				startActivity(new Intent(this, BotonActualizar.class));
				break;
			case 11:
				startActivity(new Intent(this, CrearLayout.class));
				break;
			case 12:
				startActivity(new Intent(this, AplicarEstilo.class));
				break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** OBJETO PARA CADA ITEM DEL LIST VIEW **/
	
	public class ObjetoEntradas {
		
		
		private int id;
		private String titulo;
		private String descripcion;
		
		
		public ObjetoEntradas(int id, String titulo, String descripcion) {
			this.id = id;
			this.titulo = titulo;
			this.descripcion = descripcion;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		
		public void setTitulo(String titulo){
			this.titulo = titulo;
		}
		public String getTitulo(){
			return titulo;
		}
		
		
		public void setDescripcion(String descripcion){
			this.descripcion = descripcion;
		}
		public String getDescripcion(){
			return descripcion;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	/** ADAPTADOR PARA EL LISTVIEW DEL MENU PRINCIPAL **/
	
	public class EntradasAdapter extends BaseAdapter {
		
		
		protected Activity activity;
		protected ArrayList<ObjetoEntradas> items;
		
		
		public EntradasAdapter(Activity activity, ArrayList<ObjetoEntradas> items) {
			this.activity = activity;
			this.items = items;
		}
		
		
		public int getCount() {
			return items.size();
		}
		public Object getItem(int position) {
			return items.get(position);
		}
		public long getItemId(int position) {
			return items.get(position).getId();
		}
		
		
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			
	        if(vi == null) {
	        	LayoutInflater inflater = activity.getLayoutInflater();
	        	vi = inflater.inflate(R.layout.plantilla_listview, null);
	        }
	        
	        ObjetoEntradas item = items.get(position);
	             
	        TextView titulo = (TextView) vi.findViewById(R.id.titulo);
	        titulo.setText(item.getTitulo());
	             
	        TextView descripcion = (TextView) vi.findViewById(R.id.descripcion);
	        descripcion.setText(item.getDescripcion());
	     
	        return vi;
		}
		
	}
	
	
	
	
	
}