package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistanceDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employes");
    EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	
	
     
}
