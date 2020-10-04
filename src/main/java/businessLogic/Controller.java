package businessLogic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import databaseManagement.FormDao;
import domainModel.BuilderFactory;
import formModels.CurriculumForm;
import domainModel.Builder;
import exportLibrary.DocExt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Controller {

    private static final BuilderFactory builderFactory;
    private static final EntityManagerFactory emf;

    static {
        builderFactory = new BuilderFactory();
        emf = Persistence.createEntityManagerFactory("exportlibrary");
    }

    public static JSONArray getDBCategories() {
        // Chiamo il Dao che fa la query sulle categorie e me le restituisce
        JSONArray categories = new JSONArray();

        categories.add("Curriculum");
        categories.add("Graphs");
        categories.add("Report");
        categories.add("Brochure");

        FormDao formDao = new FormDao(getEntityManager());

        formDao.persist(new CurriculumForm("curriculumWordTemplate3", "Curriculum"));

        return formDao.getCategories();
    }

    public static JSONArray getDBFormCategory(String category) {
        // Dao con category genera il form adatto
        JSONArray form = new JSONArray();
        JSONObject tmp = new JSONObject();

        tmp.put("type", "textBox");
        tmp.put("label", "firstname");
        tmp.put("value", "null");
        form.add(tmp);

        tmp = new JSONObject();
        tmp.put("type", "textBox");
        tmp.put("label", "lastname");
        tmp.put("value", "null");
        form.add(tmp);

        tmp = new JSONObject();
        tmp.put("type", "date");
        tmp.put("label", "dateofBirth");
        tmp.put("value", "null");
        form.add(tmp);

        tmp = new JSONObject();
        tmp.put("type", "textBox");
        tmp.put("label", "address");
        tmp.put("value", "null");
        form.add(tmp);

        System.out.println(category);
        System.out.println(form);

        return form;
    }

    public void generateDocument(DocExt ext, String fileName, String templateName) {
        Builder b = this.builderFactory.createBuilder(ext);
        try {
            b.generateDoc(fileName, templateName);
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



}
