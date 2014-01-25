package com.iessanandres.proyectomultimedia;

public class Opcion {
	private String nombre;
	private int numero;
	
	//contructor implicito de java
	public Opcion() {
		
	}
	
	public Opcion(String n,int nu){
		if(n!=null){
		this.nombre=n;
		}
		this.numero=nu;
	}
	
	public String getNombre() {
		return nombre;
	}
public void setNombre(String nombre) {
	this.nombre = nombre;
}	
public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
}

}
