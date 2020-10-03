package businessLogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import domainModel.Builder;
import domainModel.BuilderFactory;
import exportLibrary.DocExt;
import exportLibrary.Utils;
import formModels.Form;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import databaseManagement.CategoryDao;


public class Controller {

    private final BuilderFactory builderFactory;

    public Controller() {
        this.builderFactory = new BuilderFactory();
    }

    public static JSONArray getDBCategories() {
        // Chiamo il Dao che fa la query sulle categorie e me le restituisce
        JSONArray categories = new JSONArray();

        categories.add("Curriculum");
        categories.add("Graphs");
        categories.add("Report");
        categories.add("Brochure");

        CategoryDao categoryDao = new CategoryDao();

        return categoryDao.categories();
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



}
