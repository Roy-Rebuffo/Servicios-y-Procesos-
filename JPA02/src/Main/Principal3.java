//todos los departamentos que esten en madrid

//todos los empleados del dpt 10 y aumentar el salario

package Main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.dept;
import Model.emp;

public class Principal3 {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAscott"); //nombre de la unidad de persistencia
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		transaccion.begin();
		
		/*SELECT * FROM emp e
		join dept d on e.deptno = d.DEPTNO
		where e.DEPTNO like 10
		;*/
		/*
		TypedQuery<dept> consulta = 
				em.createQuery("SELECT d from emp e "
						+ "join dept d on e.DEPTNO = d.DEPTNO where e.DEPTNO like:vdeptno",dept.class);
		consulta.setParameter("vdeptno", 10);*/
		
		TypedQuery<emp> consulta = 
				em.createQuery("SELECT d from emp d where d.DEPTNO like:vdeptno ",emp.class);
		consulta.setParameter("vdeptno", 10);
		
		List<emp> lista = consulta.getResultList();
		
		for (emp e : lista) {
			 //e.setSAL(e.getSAL() * 5); // aumenta el salario 5 veces
			 e.setSAL((float)(e.getSAL() * 1.10)); // aumenta un 10%
			 
			System.out.println(e);
		}
		transaccion.commit();
		em.close();
	}

}
