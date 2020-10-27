package docBuilders;

import exportLibrary.DocExt;
import java.util.Arrays;


public class BuilderFactory {

    public BuilderFactory() { }

    public Builder createBuilder(DocExt ext) {

        if(Arrays.asList(DocExt.values()).contains(ext)) {
            if (ext.equals(DocExt.DOCX)) {
                return new DocxBuilder();
            } else if (ext.equals(DocExt.XLSX)) {
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
