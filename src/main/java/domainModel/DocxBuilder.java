package domainModel;

import exportLibrary.DocExt;
import exportLibrary.Utils;

import formModels.Form;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


public class DocxBuilder implements Builder {

    /**
     * Attributes: reference to the Utils class for context replacement
     *             reference to the DocExt enumeration sets the output extension
     */
    private final Utils util;
    private final DocExt outExt;

    /**
     * Constructor: instantiates the Utils class object and sets the extension
     */
    public DocxBuilder() {
        this.util = new Utils();
        this.outExt = DocExt.DOCX;
    }

    /**
     * This it the actual method for building the document. The hard work is completed
     * inside the Utils class. Here, given a .json file (later will be a String), it is
     * parsed in order to extract the fields that have to be replaced.
     * @param jsonName a Json file containing the words to replace in the template
     * @param templateName name of the template to read from the "templates" folder
     */
    @Override
    public void generateDoc(String jsonName, String templateName) throws IOException, ParseException {

        JSONParser jParser = new JSONParser();
        JSONArray fields = (JSONArray)jParser.parse(new FileReader(jsonName));
        try {
            Form f = util.mapJsonToObject(fields);
            util.insertFields(f, templateName, util.getFileExtension(templateName), this.outExt);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

    }

}
