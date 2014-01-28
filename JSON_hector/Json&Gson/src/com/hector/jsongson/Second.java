package com.hector.jsongson;

import java.io.FileWriter;
import java.security.acl.LastOwnerException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity {
	
	private EditText nombre,ape1,ape2;
	private ImageButton addPersona,mostrarDatos,guardarDatos;
	private GestionPersonas personas;
	private TextView result,resultnombre,resultape1,resultape2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		
		nombre = (EditText) findViewById(R.id.nombre);
		ape1 = (EditText) findViewById(R.id.apellido1);
		ape2 = (EditText) findViewById(R.id.apellido2);
		addPersona = (ImageButton) findViewById(R.id.imageButtonAddPersona);
		personas = new GestionPersonas();
		
		result = (TextView) findViewById(R.id.textResultJson);
		resultnombre = (TextView) findViewById(R.id.textViewNombre);
		resultape1 = (TextView) findViewById(R.id.textViewApe1);
		resultape2 = (TextView) findViewById(R.id.textViewApe2);
		
		
//		SharedPreferences prefe = getApplicationContext().getSharedPreferences("json", Context.MODE_PRIVATE);
		// objetoJson que contendra el array
		final JSONObject json = new JSONObject();
		final JSONArray lasPersonas = new JSONArray();
		
		addPersona.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String nuevoNombre = nombre.getText().toString();
				String nuevoApe1 = ape1.getText().toString();
				String nuevoApe2 = ape2.getText().toString();
				
				Persona p = new Persona(nuevoNombre,nuevoApe1,nuevoApe2);
				personas.addPersonas(p);

				
				
				//*****************************
				//con GSON agregamos el array:
				
				//String formatoJSON = gson.toJson(personas.getPersonas());
				
				//****************************
				
				// Metemos el objeto en el array
				lasPersonas.put(addObjectJsontoArray());
				
				
				// lo metemos en el contenedor
				try {
					json.put("personas", lasPersonas);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//***************************************************************
				
				// Muestro datos del contacto añadido en un toast, los extraigo del JSON
				
				
				
				result.setText(json.toString());
				
				
			}
		});
		
		
	}
	
	// metodo que devuelve el obketo en una cadena
	public String addObjectJsontoArray(){
		JSONObject persona = new JSONObject();
		try {
			persona.put("nombre", 
					personas.getPersonas().get(personas.getPersonas().size()-1).getNombre().toString());
			persona.put("ape1",
					personas.getPersonas().get(personas.getPersonas().size()-1).getApellido1().toString());
			persona.put("ape2",
					personas.getPersonas().get(personas.getPersonas().size()-1).getApellido2()).toString();
			
			
			
			String nombreC = persona.getString("nombre"); 
			String ape1C = persona.getString("ape1");
			String ape2C = persona.getString("ape2");
			
			resultnombre.setText(nombreC);
			resultape1.setText(ape1C);
			resultape2.setText(ape2C);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		result.setText(persona.toString());
		
		
		return persona.toString();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
