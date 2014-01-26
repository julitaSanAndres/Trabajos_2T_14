package com.hector.jsongson;

import java.util.ArrayList;

public class GestionPersonas {

	private ArrayList<Persona> personas;

	public GestionPersonas() {
		super();
		
		personas = new ArrayList<Persona>();
		
	}
	
	public void addPersonas(Persona p){
		
		personas.add(p);
		
	}
	
	public void deletePersona(int i) {
		
		personas.remove(i);
		
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	
	
	
	
}
