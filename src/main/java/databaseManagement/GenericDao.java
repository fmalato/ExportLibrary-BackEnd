package databaseManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class GenericDao<T> {

    public static final String SELECT_ALL_FROM_ENTITY_TABLE = "FROM %s";
    private static EntityManagerFactory emf;

    protected final EntityManager entityManager;
    protected Class<T> persistentClass;

    public GenericDao() {
        emf = Persistence.createEntityManagerFactory("exportlibrary");
        this.entityManager = emf.createEntityManager();
    }

    public void setClass(Class<T> persistenceClass) {
        this.persistentClass = persistenceClass;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void checkIfInitialized() {
        if (persistentClass == null)
            throw new IllegalStateException("No class has been specified, use setClass()");
    }

    public T findById(Long id) {
        checkIfInitialized();

        if (id == null)
            throw new IllegalArgumentException();

        entityManager.getTransaction().begin();
        T entity = entityManager.find(persistentClass, id);
        entityManager.getTransaction().commit();

        return entity;
    }

    public List<T> findAll() {
        checkIfInitialized();
        entityManager.getTransaction().begin();
        List<T> resultList = entityManager.createQuery(
                String.format(SELECT_ALL_FROM_ENTITY_TABLE,
                        persistentClass.getName()), persistentClass)
                .getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    public void persist(T entity){
        checkIfInitialized();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void merge(T entity){
        checkIfInitialized();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public void delete(T entity) {
        checkIfInitialized();
        entityManager.getTransaction().begin();
        if(!entityManager.contains(entity))
            entity = entityManager.merge(entity);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

}
