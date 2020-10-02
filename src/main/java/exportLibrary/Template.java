package exportLibrary;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Iterator;


public class Template {

    private HashMap<String, Object> fields;

    public Template(JSONArray fields) {

        this.fields = new HashMap<>();
        Iterator jsonArrayItr = fields.iterator();

        while(jsonArrayItr.hasNext()) {
            JSONObject nextObj = (JSONObject)jsonArrayItr.next();
            this.fields.put(nextObj.get("label").toString(), nextObj.get("value"));
        }

    }

    public Object getValue(String key) {
        return this.fields.get(key);
    }

    public HashMap<String, Object> getMap() {
        return this.fields;
    }

}
