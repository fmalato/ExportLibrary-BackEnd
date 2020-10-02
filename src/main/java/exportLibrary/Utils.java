package exportLibrary;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.json.simple.JSONArray;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;


public class Utils {

    private final DocExt[] supportedExts;

    public Utils() {
        this.supportedExts = DocExt.values();
    }

    /**
     * Replaces fields values in the template
     * @param fields A JsonObject containing the template fields values
     * @param docName the name of the template
     * @param inExt the input extension of the file
     * @param outExt the desired extension of the output file
     */
    public void insertFields(JSONArray fields, String docName, DocExt inExt, DocExt outExt) {

        if(Arrays.asList(this.supportedExts).contains(inExt)) {
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(docName));
                IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

                IContext context = report.createContext();
                Template template = new Template(fields);
                HashMap<String, Object> hashFields = template.getMap();
                hashFields.forEach(context::put);
                String[] list = {"un", "due", "tre", "stella!"};
                context.put("entries", list);

                OutputStream out = new FileOutputStream(new File("outFileXDoc.docx"));
                report.process(context, out);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XDocReportException e) {
                e.printStackTrace();
            }
        }
        else {
            // This should throw something like "FileNotSupportedException"
        }

    }

    /**
     * Simple utility method to get the file extension from its name
     * @param fileName the name of the file
     * @return DocExt the extension as an enumeration
     */
    public DocExt getFileExtension(String fileName) {

        String extension = "";
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            extension = fileName.substring(index + 1);
        }
        if(extension.equals("docx")) {
            return DocExt.DOCX;
        }
        else if(extension.equals("xlsx")) {
            return DocExt.XLSX;
        }
        else {
            return null;
        }
    }

}
