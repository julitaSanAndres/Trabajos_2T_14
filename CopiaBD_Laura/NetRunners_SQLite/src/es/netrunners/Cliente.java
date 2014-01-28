package es.netrunners;
/**
 * @author Miguel S. Mendoza
 *
 */
public class Cliente {

	private int ID;
	private String Nombre;
	private String Apellidos;
	private int Edad;

	public Cliente() {
		setID(0);
		setNombre("");
		setApellidos("");
		setEdad(0);
	}

	public Cliente(String nombre, String apellidos, int edad) {
		setID(0);
		setNombre(nombre);
		setApellidos(apellidos);
		setEdad(edad);
	}

	public Cliente(int ID, String nombre, String apellidos, int edad) {
		setID(ID);
		setNombre(nombre);
		setApellidos(apellidos);
		setEdad(edad);
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return Apellidos;
	}

	/**
	 * @param apellidos
	 *            the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return Edad;
	}

	/**
	 * @param edad
	 *            the edad to set
	 */
	public void setEdad(int edad) {
		Edad = edad;
	}

}
