package businessLogic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import formModels.Form;
import org.json.simple.JSONArray;

public class JsonMapper {

    public static JSONArray toJson(Form form) {

        JSONArray jsonForm = new JSONArray();

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(form);
            System.out.println("ResultingJSONstring = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonForm;
    }

}
