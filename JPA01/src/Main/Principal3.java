package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Model.ALUMNOS;

public class Principal3 {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAnuevo"); //nombre de la unidad de persistencia
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		transaccion.begin();
		//ALUMNOS a = new ALUMNOS("72737465M","Mar","Clara",24);
		
		
		//a.setEdad(33);
		//em.merge(a); // insertar si no esta o update
		ALUMNOS b = new ALUMNOS("5555","La","Tusa",18);
		em.merge(b);

		
		
		transaccion.commit();
		em.close();

	}

}
