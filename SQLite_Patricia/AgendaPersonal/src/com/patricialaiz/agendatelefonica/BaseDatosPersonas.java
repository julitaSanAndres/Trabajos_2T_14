package com.patricialaiz.agendatelefonica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
 * @author Patricia Laiz
 * 
 *indicamos el formato que ha de tener nuestra tabla Personas
 */
public class BaseDatosPersonas extends SQLiteOpenHelper{
	// version de nuestra vase de datos
	private static final int VERSION_BASEDATOS = 1;
    // Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "basedatospersonas.db";
	//Sentencia SQL para crear la tabla de Personas
    private static final String TABLA_PERSONAS = "CREATE TABLE Personas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT," +
    		" telefono INTEGER, email TEXT, notas TEXT)";
    
    // CONSTRUCTOR de la clase
    public BaseDatosPersonas(Context context, String name, CursorFactory factory,
			int version) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

	@Override
    public void onCreate(SQLiteDatabase db) {
    	//Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(TABLA_PERSONAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	//Borramos la tabla si existe y creamos una nueva.
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PERSONAS);
        onCreate(db);
    }
}
