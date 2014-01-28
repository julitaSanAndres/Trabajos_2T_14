package es.netrunners.db.Clientes;
/**
 * @author Miguel S. Mendoza
 *
 */
public class ClientesTable implements ClientesColumns {

	public final static String TABLE_NAME = "clientes";

	public final static String[] COLS = { ClientesColumns._ID,
			ClientesColumns.NOMBRE, ClientesColumns.APELLIDOS,
			ClientesColumns.EDAD };

	public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NOMBRE + " TEXT NOT NULL, " + APELLIDOS + " TEXT, " + EDAD
			+ " NUMERIC );";

}
