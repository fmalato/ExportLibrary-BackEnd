package businessLogic;

import formModels.Form;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonMapper {

    public static JSONArray toJson(Form form) {

        JSONArray jsonForm = new JSONArray();

        try {
            System.out.println(form.getFields());
            String jsonString = form.getFields();
            JSONParser parser = new JSONParser();
            jsonForm = (JSONArray) parser.parse(jsonString);
            System.out.println(jsonForm);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonForm;
    }

    public static JSONObject createField(String label, String type) {
        JSONObject field = new JSONObject();
        field.put("label", label);
        field.put("type", type);
        field.put("value", null);

        return field;
    }

}
