package businessLogic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import formModels.Form;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonMapper {

    public static JSONArray toJson(Form form) {

        JSONArray jsonForm = new JSONArray();

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(form);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

            jsonObject.remove("id");
            jsonObject.remove("name");
            jsonObject.remove("category");

            JSONObject jsonTmp = null;
            for (Object key : jsonObject.keySet()) {
                jsonTmp = new JSONObject();
                jsonTmp.put("type", "text");
                jsonTmp.put("label", key);
                jsonTmp.put("value", null);
                jsonForm.add(jsonTmp);
            }

        } catch (JsonProcessingException | ParseException e) {
            e.printStackTrace();
        }

        return jsonForm;
    }

}
