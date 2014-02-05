import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tablas.City;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Metodo 1:
		//******************************************************************************
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session sesion= factory.openSession();
		Transaction tx= sesion.beginTransaction();
		
		System.out.println("Inserto una fila en la tabla ciudades:");
		
		City ciudad= new City();
		ciudad.setId(0);
		ciudad.setCountryCode("ESP");
		ciudad.setDistrict("Castilla y Leon");
		ciudad.setName("LegioVII");
		ciudad.setPopulation(131680);
		
		sesion.save(ciudad);		
		tx.commit();
		
		sesion.close();
		//******************************************************************************
		
	}

}
