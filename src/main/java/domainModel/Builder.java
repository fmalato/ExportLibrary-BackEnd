package domainModel;

import exportLibrary.Utils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public interface Builder {

    default File generateDoc(JSONArray jArray, String templateName) throws IOException, ParseException {
        return null;
    }

}
