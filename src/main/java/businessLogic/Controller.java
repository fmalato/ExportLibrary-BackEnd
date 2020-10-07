package businessLogic;

import formModels.Form;
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

        FormDao formDao = new FormDao(getEntityManager());

        // formDao.persist(new CurriculumForm("curriculumWordTemplate3", "Curriculum"));

        return formDao.getCategories();
    }

    public static JSONArray getDBFormCategory(String category) {

        FormDao formDao = new FormDao(getEntityManager());
        Form form = formDao.getFormFromCategory(category);
        JSONArray jsonForm = JsonMapper.toJson(form);

        return jsonForm;
    }

    public static JSONArray getDBTemplateNameCategory(String category) {

        FormDao formDao = new FormDao(getEntityManager());

        return  formDao.getTemplatesFromCategory(category);
    }

    public static void generateDocument(DocExt ext, String fileName, String templateName) {
        Builder b = builderFactory.createBuilder(ext);
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
