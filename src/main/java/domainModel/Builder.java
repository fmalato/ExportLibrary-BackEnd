package domainModel;

import exportLibrary.Utils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Builder {

    default File generateDoc(ArrayList jArray, String templateName) throws IOException, ParseException {
        return null;
    }

}
