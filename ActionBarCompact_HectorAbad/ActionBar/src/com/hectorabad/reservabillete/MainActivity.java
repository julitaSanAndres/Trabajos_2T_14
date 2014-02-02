package com.hectorabad.reservabillete;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private EditText fechaSalida;
	private EditText fechaLlegada;
	private RadioButton ida;
	private RadioButton idaYVuelta;
	private Spinner ciudadOrigen;
	private Spinner ciudadDestino;
	private ImageButton fechaSalidaImagen;
	private ImageButton fechaLlegadaImagen;
	private RadioGroup radios;
	private TextView resultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		idaYVuelta = (RadioButton) findViewById(R.id.radioIdaYVuelta);
		ciudadOrigen = (Spinner) findViewById(R.id.spinnerOrigen);
		ciudadDestino = (Spinner) findViewById(R.id.spinnerDestino);
		fechaLlegadaImagen = (ImageButton) findViewById(R.id.botonCalendarioLlegada);
		fechaSalidaImagen = (ImageButton) findViewById(R.id.botonCalendarioSalida);
		fechaSalida = (EditText) findViewById(R.id.editFechaSalida);
		fechaLlegada = (EditText) findViewById(R.id.editLlegada);
		ida = (RadioButton) findViewById(R.id.radioIda2);
		radios = (RadioGroup) findViewById(R.id.radioGroup1);
		resultado = (TextView) findViewById(R.id.resultado);
		radios.clearCheck();
		fechaLlegada.setEnabled(false);
		fechaLlegadaImagen.setEnabled(false);
		fechaSalida.setEnabled(false);
		fechaSalidaImagen.setEnabled(false);
		// Spinners
		// --------------------------------------------------------------------------
		ArrayAdapter<CharSequence> adaptadorProvincias1 = ArrayAdapter
				.createFromResource(this, R.array.ciudades,
						android.R.layout.simple_spinner_item);
		adaptadorProvincias1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ciudadOrigen.setAdapter(adaptadorProvincias1);

		ArrayAdapter<CharSequence> adaptadorProvincias2 = ArrayAdapter
				.createFromResource(this, R.array.ciudades,
						android.R.layout.simple_spinner_item);
		adaptadorProvincias2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ciudadDestino.setAdapter(adaptadorProvincias2);
		// --------------------------------------------------------------------------
		// RadioButton
		// --------------------------------------------------------------------------
		radios.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				// TODO Auto-generated method stub
				if (ida.isChecked() == true) {
					fechaLlegada.setEnabled(false);
					fechaLlegadaImagen.setEnabled(false);
					fechaSalida.setEnabled(true);
					fechaSalidaImagen.setEnabled(true);
				} else if (idaYVuelta.isChecked() == true) {
					fechaLlegada.setEnabled(true);
					fechaLlegadaImagen.setEnabled(true);
					fechaSalida.setEnabled(true);
					fechaSalidaImagen.setEnabled(true);
				}
			}
		});
		// --------------------------------------------------------------------------
	}
	//Aqui es donde asignamos es menu a nuetra actividad

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void fechaCalendarioSalida(View v) {
		DatePickerFragment newFragment = new DatePickerFragment(fechaSalida);
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	public void fechaCalendarioLlegada(View v) {
		DatePickerFragment newFragment = new DatePickerFragment(fechaLlegada);
		newFragment.show(getSupportFragmentManager(), "datePicker");

	}



	public void metodoFinalizar(View v) {
		if (ida.isChecked() == true) {
			resultado.setText("Tipo de Billete: Ida" + "\n" + "Ciudad Origen: "
					+ ciudadOrigen.getSelectedItem() + "\n"
					+ "Ciudad Destino: " + ciudadDestino.getSelectedItem()
					+ "\n" + "Fecha Salida :" + fechaSalida.getText());
		} else if (idaYVuelta.isChecked() == true) {
			resultado.setText("Tipo de Billete: Ida y Vuelta" + "\n"
					+ "Ciudad Origen: " + ciudadOrigen.getSelectedItem() + "\n"
					+ "Ciudad Destino: " + ciudadDestino.getSelectedItem()
					+ "\n" + "Fecha Salida :" + fechaSalida.getText() + "\n"
					+ "Fecha Llegada: " + fechaLlegada.getText());
		}

	}
	//Este metodo sirve para que las opciones del menu tenga funcionalidad
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_realizado:
			Log.i("ActionBar", "Realizado");
			;
			Toast toast1 = Toast.makeText(getApplicationContext(),
					"Realizado por: Héctor Abad Omaña", Toast.LENGTH_SHORT);
			toast1.show();

			return true;
		case R.id.menu_localizacion:
			Log.i("ActionBar", "Localizacion");
			;
			Toast toast2 = Toast.makeText(getApplicationContext(),
					"Se encuentra en : IES SAN ANDRES", Toast.LENGTH_SHORT);
			toast2.show();
			return true;
		case R.id.menu_actualizar:
			Log.i("ActionBar", "Localizacion");
			;
			Toast toast3 = Toast.makeText(getApplicationContext(),
					"Actualizando", Toast.LENGTH_SHORT);
			toast3.show();
			return true;
		case R.id.menu_buscar:
			Log.i("ActionBar", "Localizacion");
			;
			Toast toast4 = Toast.makeText(getApplicationContext(), "Buscando",
					Toast.LENGTH_SHORT);
			toast4.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
