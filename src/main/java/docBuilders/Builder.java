package docBuilders;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public interface Builder {

    default byte[] generateDoc(ArrayList jArray, String templateName, boolean toBeZipped) throws IOException, ParseException {
        return null;
    }

}
