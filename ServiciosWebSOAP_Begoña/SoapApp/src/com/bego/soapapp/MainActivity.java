package com.bego.soapapp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private RadioGroup rgOptions;	//rB0: ºC --> ºF;  rB1: ºF --> ºC
	private EditText value;
	private TextView carga, result;
	private Button btnConvert, color;
	private LinearLayout fondo;
	private int colores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fondo = (LinearLayout)findViewById(R.id.LinearLayout1);
		rgOptions = (RadioGroup)findViewById(R.id.radioGroup1);
		value = (EditText)findViewById(R.id.edtxValue);
		result = (TextView)findViewById(R.id.txvRes);
		carga = (TextView)findViewById(R.id.texvCarga);
		btnConvert = (Button)findViewById(R.id.btnConvert);
		color = (Button)findViewById(R.id.btnColor);
		
		/**
		 * Accederemos al web service mediante una tarea asíncrona porque a partir de la 
		 * versión de Android 3.0 no se pueden realizar operaciones de larga duración 
		 * directamente en el hilo principal de la aplicación. Si lo hicieramos, saltaría la 
		 * excepción NetworkOnMainThread
		 */
		btnConvert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(value.getText().toString().length() > 0){
					if(rgOptions.getCheckedRadioButtonId() == R.id.rbCf){
						//ºCelsius --> ºFahrenheit
						CelsToFah asynCF = new CelsToFah();
						asynCF.execute();
						carga.setText("Conectando con el servicio web...");
					}else{
						//ºFahrenheit --> ºCelsius
						FahToCels asynFC = new FahToCels();
						asynFC.execute();
						carga.setText("Conectando con el servicio web...");
					}
				}else{
					Toast.makeText(getApplicationContext(), "Indique un valor válido", 
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		color.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (colores) {
					case 0:
						fondo.setBackgroundColor(getResources().getColor(R.color.color_blue_light));
						colores++;
						break;
					case 1:
						fondo.setBackgroundColor(getResources().getColor(R.color.color_orange));
						colores++;
						break;
					case 2:
						fondo.setBackgroundColor(getResources().getColor(R.color.color_withe));
						colores = 0;
						break;
				}
			}
		});
		
		value.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				result.setText("");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Conecta con el servicio web en segundo plano para convertir los grados Celsius a Fahrenheit
	 */
	private class CelsToFah extends AsyncTask<String,Void,String> {
		private String resObj;
	 
	    protected String doInBackground(String... params) {
	    	//Constantes SOAP para ºC --> ºF
			final String NAMESPACE = "http://www.w3schools.com/webservices/";
			final String URL="http://www.w3schools.com/webservices/tempconvert.asmx";
			final String METHOD_NAME = "CelsiusToFahrenheit";
			final String SOAP_ACTION = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
			
			//En primer lugar crearemos la petición (request) al método deseado, mediante 
			//un objeto SoapObject al que le añadiremos los parámetros necesarios para la consulta.
			//La clave de cada parámetro viene dada por el webService y diferencia entre 
			//mayúsculas y minúsculas.
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("Celsius", value.getText().toString());
			
			//El segundo paso será crear el contenedor SOAP (envelope) y asociarle nuestra petición. 
			//Para ello crearemos un nuevo objeto SoapSerializationEnvelope indicando la versión de 
			//SOAP que vamos a usar. Indicaremos además que se trata de un servicio web .NET 
			//activando su propiedad dotNet. Por último, asociaremos la petición antes creada a 
			//nuestro contenedor llamando al método setOutputSoapObject().
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			
			//Como tercer paso crearemos el objeto que se encargará de realizar la comunicación HTTP 
			//con el servidor, de tipo HttpTransportSE, al que pasaremos la URL de conexión a nuestro 
			//servicio web. Por último, completaremos el proceso realizando la llamada al servicio 
			//web mediante el método call()
			HttpTransportSE transporte = new HttpTransportSE(URL);
			try{
				transporte.call(SOAP_ACTION, envelope);
				SoapPrimitive resultado_xml = (SoapPrimitive)envelope.getResponse();
				resObj = resultado_xml.toString();
			}catch(Exception e){
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}
	        return resObj;
	    }

	    protected void onPostExecute(String r) {
	    	carga.setText("");
	    	result.setText(resObj + " C");
	    }
	}
	
	/**
	 * Conecta con el servicio web en segundo plano para convertir los grados Fahrenheit a Celsius
	 */
	private class FahToCels extends AsyncTask<String,Void,String> {
		private String resObt;
		
		protected String doInBackground(String... params) {
	    	//Constantes SOAP para ºF --> ºC
			final String NAMESPACE = "http://www.w3schools.com/webservices/";
			final String URL="http://www.w3schools.com/webservices/tempconvert.asmx";
			final String METHOD_NAME = "FahrenheitToCelsius";
			final String SOAP_ACTION = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
			
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("Fahrenheit", value.getText().toString());
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE transporte = new HttpTransportSE(URL);
			try{
				transporte.call(SOAP_ACTION, envelope);
				SoapPrimitive resultado_xml = (SoapPrimitive)envelope.getResponse();
				resObt = resultado_xml.toString();
			}catch(Exception e){
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}
	        return resObt;
	    }

	    protected void onPostExecute(String r) {
	    	carga.setText("");
	    	result.setText(resObt + " F");
	    }
	}
}
