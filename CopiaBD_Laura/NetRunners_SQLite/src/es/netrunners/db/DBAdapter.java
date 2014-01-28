package es.netrunners.db;

import java.util.ArrayList;

import es.netrunners.Cliente;
import es.netrunners.db.Clientes.ClientesColumns;
import es.netrunners.db.Clientes.ClientesTable;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.provider.BaseColumns;
/**
 * @author Miguel S. Mendoza
 *
 */
public class DBAdapter extends Service {

	private final IBinder mBinder = new LocalBinder();

	private DBHelper dbHelper;

	private SQLiteDatabase db;

	public class LocalBinder extends Binder {
		public DBAdapter getService() {
			return DBAdapter.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		dbHelper = new DBHelper(this);
		// try {
		// db = dbHelper.getWritableDatabase();
		// } catch (SQLiteException ex) {
		// db = dbHelper.getReadableDatabase();
		// }
		db = dbHelper.getDataBase();
	}

	@Override
	public void onDestroy() {
		db.close();
	}

	public Cursor getCursorItem(long _rowIndex) throws SQLException {
		Cursor result = db.query(true, ClientesTable.TABLE_NAME,
				ClientesTable.COLS, BaseColumns._ID + "=" + _rowIndex, null,
				null, null, null, null);
		if ((result.getCount() == 0) || !result.moveToFirst()) {
			throw new SQLException("No items found for row: " + _rowIndex);
		}
		return result;
	}

	/**
	 * Obtiene un cursor con todos los clientes de la base de datos
	 */
	public Cursor getCursorClientes() {
		return db.query(ClientesTable.TABLE_NAME, ClientesTable.COLS, null,
				null, null, null, BaseColumns._ID);
	}

	public ArrayList<Cliente> getAllClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Cursor result = db.query(ClientesTable.TABLE_NAME, ClientesTable.COLS,
				null, null, null, null, BaseColumns._ID);
		if (result.moveToFirst())
			do {
				clientes.add(new Cliente(result.getInt(result
						.getColumnIndex(BaseColumns._ID)), result
						.getString(result
								.getColumnIndex(ClientesColumns.NOMBRE)),
						result.getString(result
								.getColumnIndex(ClientesColumns.APELLIDOS)),
						result.getInt(result
								.getColumnIndex(ClientesColumns.EDAD))));
			} while (result.moveToNext());
		return clientes;
	}

	/**
	 * INSERTAR NUEVO CLIENTE
	 * */
	public long insertCliente(String nombre, String apellidos, Integer edad) {
		ContentValues newValues = new ContentValues();
		newValues.put(ClientesColumns.NOMBRE, nombre);
		newValues.put(ClientesColumns.APELLIDOS, apellidos);
		newValues.put(ClientesColumns.EDAD, edad);
		return db.insert(ClientesTable.TABLE_NAME, null, newValues);
	}

	/**
	 * BORRAR CLIENTE CON _id = _rowIndex
	 * */
	public boolean deleteCliente(int _rowIndex) {
		return db.delete(ClientesTable.TABLE_NAME, BaseColumns._ID + "="
				+ _rowIndex, null) > 0;
	}

	/**
	 * ACTUALIZAR CIENTE _id = _rowIndex
	 * */
	public boolean updateCliente(Integer _rowIndex, String nombre,
			String apellidos, Integer edad) {
		ContentValues newValues = new ContentValues();
		newValues.put(ClientesColumns.NOMBRE, nombre);
		newValues.put(ClientesColumns.APELLIDOS, apellidos);
		newValues.put(ClientesColumns.EDAD, edad);
		return db.update(ClientesTable.TABLE_NAME, newValues, BaseColumns._ID
				+ "=" + _rowIndex, null) > 0;
	}

}
