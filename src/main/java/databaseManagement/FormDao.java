package databaseManagement;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import formModels.Form;

public class FormDao {

    private EntityManager entityManager;

    public FormDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveForm(Form form) {
        System.out.println(entityManager);
        entityManager.getTransaction().begin();
        entityManager.persist(form);
        entityManager.getTransaction().commit();
    }

}
