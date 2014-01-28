package com.patricialaiz.agendatelefonica;

import java.io.Serializable;

/**
 * 
 * @author Patricia Laiz
 * 
 *será la encargada de mostrarnos los datos.
 *El id sera lo unico que no podremos modificar ya que los generamos automaticamente
 */
public class Persona  implements Serializable{

	private int id;
	private String nombre;
	private int telefono;
	private String email;
	private String notas;
	
	/**
	 * constructores 
	 */
	
	public Persona() {
		super();
	}

	public Persona(int id, String nombre, int telefono, String email,
			String notas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.notas = notas;
	}
/**
 * Metodos de acceso y modificacion a los datos
 * 
 */
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	
}
