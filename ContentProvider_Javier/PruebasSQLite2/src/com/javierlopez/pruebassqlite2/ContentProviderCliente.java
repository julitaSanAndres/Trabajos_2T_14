package com.javierlopez.pruebassqlite2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.widget.Toast;

public class ContentProviderCliente extends ContentProvider {

	// Definición del CONTENT_URI
	private static final String uri = "content://com.javierlopez.pruebassqlite2/productos";

	public static final Uri CONTENT_URI = Uri.parse(uri);

	// Necesario para UriMatcher
	private static final int PRODUCTOS = 1;
	private static final int PRODUCTOS_ID = 2;
	private static final UriMatcher uriMatcher;

	// Clase interna para declarar las constantes de columna
	public static final class Productos implements BaseColumns {
		private Productos() {
		}

		// Nombres de columnas
		public static final String COL_NOMBRE = "nombre";
		public static final String COL_PRECIO = "precio";
	}

	// Base de datos
	private SQLiteBase clidbh;
	private static final String BD_NOMBRE = "DBProductos";
	private static final int BD_VERSION = 1;
	private static final String TABLA_PRODUCTOS = "Productos";

	// Inicializamos el UriMatcher
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.javierlopez.pruebassqlite2", "productos",
				PRODUCTOS);
		uriMatcher.addURI("com.javierlopez.pruebassqlite2",
				"productos/#", PRODUCTOS_ID);
	}

	@Override
	public boolean onCreate() {

		clidbh = new SQLiteBase(getContext(), BD_NOMBRE, null, BD_VERSION);

		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if (uriMatcher.match(uri) == PRODUCTOS_ID) {
			where = "_id=" + uri.getLastPathSegment();//.getLastPathSegment() devuelve el ultimo segmento
			//de la cadena uri de esa manera solamente coge el valor del id del registro
		}

		//Se abre la base de datos para hacer la llamada a la tabla.
		SQLiteDatabase db = clidbh.getWritableDatabase();

		//Se obtiene el cursor con el dato o los datos que se hayan requerido
		Cursor c = db.query(TABLA_PRODUCTOS, projection, where, selectionArgs,
				null, null, sortOrder);

		//Se devuelve el cursor para que el programa principal se encargue de gestionarlo
		return c;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		long regId = 1;

		//Gestor de la base de datos
		SQLiteDatabase db = clidbh.getWritableDatabase();
		regId = db.insert(TABLA_PRODUCTOS, null, values);
		
		//Se añade nuestro nuevo registro a la tabla
		Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

		//Se devuelve el valor del uri
		return newUri;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		int cont;

		SQLiteDatabase db = clidbh.getWritableDatabase();

		cont = db.update(TABLA_PRODUCTOS, values, selection, selectionArgs);

		return cont;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		int cont;
        
        SQLiteDatabase db = clidbh.getWritableDatabase();
        
        cont = db.delete(TABLA_PRODUCTOS, selection, selectionArgs);
        
        return cont;

	}

	@Override
	public String getType(Uri uri) {

		//Se genera un match de la uri que se le pasa
		int match = uriMatcher.match(uri);

		//Se comprueba la id mediante un switch para ver que tipo de dato es
		//si se trata de una dirección o si se trata de un unico registro
		switch (match) {
		case PRODUCTOS:
			return "vnd.android.cursor.dir/vnd.javierlopez.productos";
		case PRODUCTOS_ID:
			return "vnd.android.cursor.item/vnd.javierlopez.productos";
		default:
			return null;
		}
	}

}
