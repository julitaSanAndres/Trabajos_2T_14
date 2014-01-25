package com.iessanandres.proyectomultimedia;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterProyect  extends ArrayAdapter<Opcion>{
	
	private Activity context;
	private Opcion[] datos;
	private int[]imagenes={R.drawable.music2,R.drawable.record_move,R.drawable
			.image,R.drawable.record_sound,R.drawable.video,R.drawable.camera3};
	

		 public MyAdapterProyect(Activity context, int textViewResouseId, Opcion[] n) {
			 super(context,textViewResouseId, n);
				this.context= context;
				this.datos= n;
		}

		static class ViewHolder{
			protected TextView nombre;
			protected ImageView ima;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			View iten=convertView;
			
			ViewHolder holder;//holder es titular
			
			if(convertView==null){
				//intance element inflater
				
				LayoutInflater inflater=context.getLayoutInflater();
				iten=inflater.inflate(R.layout.segundo,null);
				
				holder=new ViewHolder();
				
				holder.nombre=(TextView)iten.findViewById(R.id.textView1);
				holder.ima=(ImageView)iten.findViewById(R.id.imageView1);
				
				
				iten.setTag(holder);
				
			}else{
				holder=(ViewHolder)iten.getTag();
			}
			
			holder.nombre.setText(datos[position].getNombre());
			holder.ima.setImageResource(imagenes[datos[position].getNumero()]);
			

			
			return (iten);
		}

}
