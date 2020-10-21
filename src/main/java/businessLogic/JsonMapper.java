package businessLogic;

import formModels.Form;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonMapper {

    /**
     * This function generates a proper JSON Array from a Form class instance, thanks to getFields() Form function of
     * the base class.
     * @param form
     * @return JSONArray
     */
    public static JSONArray toJson(Form form) {

        JSONArray jsonForm = new JSONArray();

        try {
            String jsonString = form.getFields();
            JSONParser parser = new JSONParser();
            jsonForm = (JSONArray) parser.parse(jsonString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonForm;
    }


    /**
     * From a String, it returns a JSONObject field in our standard format for final json array.
     * @param label
     * @param type
     * @return JSONObject
     */
    public static JSONObject createField(String label, String type) {
        JSONObject field = new JSONObject();
        field.put("label", label);
        field.put("type", type);
        field.put("value", null);

        return field;
    }

}
