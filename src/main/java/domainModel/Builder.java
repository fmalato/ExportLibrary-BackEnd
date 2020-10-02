package domainModel;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface Builder {

    default void generateDoc(String jsonName, String templateName) throws IOException, ParseException {

    }

}
