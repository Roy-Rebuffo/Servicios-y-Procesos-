package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Model.ALUMNOS;

public class Principal2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAnuevo"); //nombre de la unidad de persistencia
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		transaccion.begin();
		//Find busca el alumno
		ALUMNOS a = em.find(ALUMNOS.class, "1234567Z"); //Encuentra al alumno
		ALUMNOS b = em.find(ALUMNOS.class, "1111");//No encuentra nada -> null
		System.out.println(a);
		System.out.println(b);
		
		//remove quita un objeto
		if (a !=null) {
			em.remove(a);
		}else {
			System.err.println("No existe");
		}
		
		
		
		transaccion.commit();
		em.close();

	}

}
