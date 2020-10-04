package databaseManagement;

import javax.persistence.EntityManager;

import formModels.Form;
import org.json.simple.JSONArray;

import java.util.ArrayList;


public class FormDao extends GenericDao<Form> {

    public FormDao(EntityManager entityManager) {
        super(entityManager);
        setClass(Form.class);
    }

    public JSONArray getCategories() {
        checkIfInitialized();
        entityManager.getTransaction().begin();
        ArrayList<String> resultList = (ArrayList<String>) entityManager.createQuery("select distinct category from Form").getResultList();
        entityManager.getTransaction().commit();

        JSONArray jsonResultList = new JSONArray();
        for (String result : resultList) {
            jsonResultList.add(result);
        }

        return jsonResultList;
    }

}
