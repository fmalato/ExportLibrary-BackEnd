package businessLogic;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import databaseManagement.FormDao;
import docBuilders.BuilderFactory;
import docBuilders.Builder;
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

        //formDao.persist(new CurriculumForm("CurriculumModelloLondra.xlsx", "Curriculum"));

        return formDao.getCategories();
    }

    public static JSONArray getDBForm(String category) {

        FormDao formDao = new FormDao(getEntityManager());
        return formDao.getForm(category);
    }

    public static JSONArray getDBTemplateNames(String category) {

        FormDao formDao = new FormDao(getEntityManager());
        return  formDao.getTemplates(category);
    }

    public static byte[] generateDocument(DocExt ext, ArrayList fields, String templateName, boolean toBeZipped) {
        Builder b = builderFactory.createBuilder(ext);
        try {
            return b.generateDoc(fields, templateName, toBeZipped);
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
