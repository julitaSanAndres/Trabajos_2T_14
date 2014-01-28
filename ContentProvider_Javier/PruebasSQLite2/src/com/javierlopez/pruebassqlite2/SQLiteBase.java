package com.javierlopez.pruebassqlite2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteBase extends SQLiteOpenHelper {

	//Sentencia que generara posteriormente la tabla con cada campo y su clave primaria
	String sqlCrear = "CREATE TABLE Productos "
			+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ " nombre TEXT, "
			+ " precio TEXT )";

	/**
	 * 
	 * @param context contexto en el que se ejecutara nuestra base de datos
	 * @param name nombre con el que se guardara en el terminal la base
	 * @param factory 
	 * @param version de la base para saber cuantas modificaciones ha tenido o en que estado esta
	 */
	public SQLiteBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		//Se ejecuta la sentencia creada anteriormente y se genera nuestra tabla
		db.execSQL(sqlCrear);

		// Insertamos 15 productos de ejemplo
		for (int i = 1; i <= 15; i++) {
			// Generamos los datos de muestra
			String nombre = "Precio" + i;
			String precio = i + "." + i;

			// Insertamos los datos en la tabla Productos
			db.execSQL("INSERT INTO Productos (nombre, precio) "
					+ "VALUES ('" + nombre + "', '" + precio + "')");
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {

		//Se borra la version anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS Productos");

		// Se crea la nueva versión de la tabla
		db.execSQL(sqlCrear);

	}

}
