package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Model.ALUMNOS;

public class Principal {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAnuevo"); //nombre de la unidad de persistencia
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		transaccion.begin();
		em.persist(new ALUMNOS("1234567Z","Pedro","Lopez",33));
		em.persist(new ALUMNOS("7654321Y","Fabian","Luna",19));
		em.persist(new ALUMNOS("33445566L","Rafa","Stalin",33));
		em.persist(new ALUMNOS("7233395X","Roy","Rebuffo",27));
		em.persist(new ALUMNOS("72737465M","Mar","Clara",24));
		
		transaccion.commit();
		em.close();

	}

}
