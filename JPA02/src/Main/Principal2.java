//todos los departamentos que esten en madrid

package Main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.dept;

public class Principal2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAscott"); //nombre de la unidad de persistencia
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		transaccion.begin();
		
		//select DNAME from dept where LOC = "Madrid";
		TypedQuery<dept> consulta = 
				em.createQuery("SELECT d from dept d where d.LOC like:vloc",dept.class);
		consulta.setParameter("vloc", "Madrid");
		
		List<dept> lista = consulta.getResultList();
		
		for (dept a : lista) {
			System.out.println(a);
		}
		transaccion.commit();
		em.close();
	}

}
