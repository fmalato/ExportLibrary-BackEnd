package businessLogic;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import databaseManagement.FormDao;
import docBuilders.BuilderFactory;
import docBuilders.Builder;


public class Controller {

    private static final BuilderFactory builderFactory;

    static {
        builderFactory = new BuilderFactory();
    }

    public static JSONArray getDBCategories() {

        FormDao formDao = new FormDao();

        //formDao.persist(new CurriculumForm("CurriculumModelloLondra.xlsx", "Curriculum"));

        return formDao.getCategories();
    }

    public static JSONArray getDBForm(String category) {

        FormDao formDao = new FormDao();
        return formDao.getForm(category);
    }

    public static JSONArray getDBTemplateNames(String category) {

        FormDao formDao = new FormDao();
        return  formDao.getTemplates(category);
    }

    public static byte[] generateDocument(ArrayList fields, String templateName, boolean toBeZipped) {
        Builder b = builderFactory.createBuilder(templateName);
        try {
            return b.generateDoc(fields, templateName, toBeZipped);
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
