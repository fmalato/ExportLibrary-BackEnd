package databaseManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import domainModel.Form;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;


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

    public JSONArray getForm(String category) {
        checkIfInitialized();
        entityManager.getTransaction().begin();
        List<Form> formList =  entityManager.createQuery(String.format("select f from Form f where f.category = '%s'",
                category), Form.class).getResultList();
        entityManager.getTransaction().commit();

        Form form = null;
        try {
            form = formList.get(0);
        } catch(EntityNotFoundException e) { System.out.println(e); }

        return JsonMapper.toJson(form);
    }

    public JSONArray getTemplates(String category) {
        checkIfInitialized();
        entityManager.getTransaction().begin();
        ArrayList<String> resultList = (ArrayList<String>) entityManager.createQuery(String.format("select distinct name from Form where category = '%s'",
                category)).getResultList();
        entityManager.getTransaction().commit();

        JSONArray jsonResultList = new JSONArray();
        for (String result : resultList) {
            jsonResultList.add(result);
        }

        return jsonResultList;
    }
}



