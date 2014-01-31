package service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Client;

/**
 * Classe générique permettant de réaliser les opérations CRUD simple pour n'importe
 * quelle entité
 *
 * @param <T>
 */
public abstract class DataAccessService<T> {

    @PersistenceContext
    private EntityManager em;

    public DataAccessService() {
    }
    
    private Class<T> type;

    public DataAccessService(Class<T> type) {
        this.type = type;
    }
    
    /**
     * Stocke une instance en base
     * @param T Object
     * @return 
     * 	Retourne l'instance persistée
     */
    public T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    /**
     * Récupère une instance en base à partir de son ID 
     * @param T Object
     * @param id
     * @return 
     * 	Retourne l'instance récupérée
     */
    public T find(Object id) {
        return this.em.find(this.type, id);
    }

    /**
     * Supprime une entrée de la base
     * @param type
     * @param id 
     */
    public void delete(Object id) {
        Object ref = this.em.getReference(this.type, id);
        this.em.remove(ref);
    }


    /**
     * Mets à jour une entrée dans la base
     * @param <T>
     * @param t
     * @return objet mis à jour
     * 
     */
    public T update(T item) {
        if( item instanceof Client){
        	Client client = (Client)item;
                if(client.getId() == 1){
                    return item;
                }
            }
        return (T) this.em.merge(item);
        
    }

    /**
     * Renvoie une liste d'objets selon une requête simple
     * @param namedQueryName
     * @return List
     */
    @SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * Renvoie une liste d'objets selon une requête paramétrée
     * @param namedQueryName
     * @param parameters
     * @return List
     */
    @SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    /**
     * Renvoie une liste d'objets avec une limite de résultats
     * @param queryName
     * @param resultLimit
     * @return List
     */
    @SuppressWarnings("rawtypes")
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    /**
     * Renvoie une liste d'objets correspondant à la requête SQL donnée et au type précisé
     * @param <T>
     * @param sql
     * @param type
     * @return List
     */
    
    @SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String sql) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    /**
     * Renvoie le nombre de résultats d'une requête
     * @param namedQueryName
     * @return int
     */
    public int countTotalRecord(String namedQueryName) {
        Query query = em.createNamedQuery(namedQueryName);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    /**
     * 
     * @param namedQueryName
     * @param parameters
     * @param resultLimit
     * @return List
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
    
    /**
     * @param namedQueryName
     * @param start
     * @param end
     * @return List
     */
    @SuppressWarnings("rawtypes")
	public List findWithNamedQuery(String namedQueryName, int start, int end) {
        Query query = this.em.createNamedQuery(namedQueryName);
        query.setMaxResults(end - start);
        query.setFirstResult(start);
        return query.getResultList();
    }
}