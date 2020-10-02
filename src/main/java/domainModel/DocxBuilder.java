package domainModel;

import exportLibrary.DocExt;
import exportLibrary.Utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class DocxBuilder implements Builder {

    /**
     * Attribute: reference to the Utils class for context replacement
     */
    private final Utils util;
    private final DocExt outExt;

    /**
     * Constructor: instantiates the Utils class object
     */
    public DocxBuilder() {
        this.util = new Utils();
        this.outExt = DocExt.DOCX;
    }

    /**
     *
     * @param jsonName a Json file containing the words to replace in the template
     * @param templateName name of the template to read from the "templates" folder
     */
    @Override
    public void generateDoc(String jsonName, String templateName) throws IOException, ParseException {

        JSONParser jParser = new JSONParser();
        JSONArray fields = (JSONArray)jParser.parse(new FileReader(jsonName));
        try {
            DocExt inExt = util.getFileExtension(templateName);

            util.insertFields(fields, templateName, inExt, this.outExt);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

    }

}
