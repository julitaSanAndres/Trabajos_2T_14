package com.hectorabad.reservabillete;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;


@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements
		OnDateSetListener {
	private EditText fechaSeleccionada;

	/**
	 * Constructor donde le pasamos el View sobre el que se desea interaccionar
	 * 
	 * @param txSa
	 *            componente
	 */
	public DatePickerFragment(EditText txSa) {
		fechaSeleccionada = txSa;
	}

	public DatePickerFragment() {
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {

		fechaSeleccionada.setText(String.format("%02d / %02d /%4d", day,
				(month + 1), year));

	}

}
