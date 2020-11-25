package docBuilders;

import exportLibrary.DocExt;
import exportLibrary.Utils;

import java.util.Arrays;

import static exportLibrary.Utils.getFileExtension;


public class BuilderFactory {

    public BuilderFactory() { }

    public Builder createBuilder(String templateName) {

        DocExt docExt = Utils.getFileExtension(templateName);

        if(Arrays.asList(DocExt.values()).contains(docExt)) {
            if (docExt.equals(DocExt.DOCX)) {
                return new DocxBuilder();
            } else if (docExt.equals(DocExt.XLSX)) {
                return new XlsxBuilder();
            } else {
                return null;
            }
        }
        else {
            return null;
        }

    }

}
