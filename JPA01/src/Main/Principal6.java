package Main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.ALUMNOS;

public class Principal6 {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAnuevo"); //nombre de la unidad de persistencia
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		TypedQuery<ALUMNOS> consulta = 
				em.createQuery("SELECT d from ALUMNOS d where d.edad>:vedad",ALUMNOS.class);
		consulta.setParameter("vedad", 25);
		
		List<ALUMNOS> lista = consulta.getResultList();
		
		for (ALUMNOS a : lista) {
			System.out.println(a);
		}
		transaccion.commit();
		em.close();

	}

}
