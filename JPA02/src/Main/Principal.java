package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Model.dept;

public class Principal {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAscott"); //nombre de la unidad de persistencia
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		transaccion.begin();
		em.persist(new dept(1,"RRHH","Madrid"));
		em.persist(new dept(2,"Marketing","Barcelona"));
		em.persist(new dept(3,"Desarrollo","Madrid"));
		em.persist(new dept(4,"I+D","Asturias"));
		
		transaccion.commit();
		em.close();

	}

}
