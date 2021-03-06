package docBuilders;

import exportLibrary.DocExt;
import exportLibrary.Utils;

import JxlsClasses.HospitalEmployee;
import JxlsClasses.TuscanyProvince;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsxBuilder implements Builder {

    private final Utils util;
    private final DocExt outExt;

    public XlsxBuilder() {
        this.util = new Utils();
        this.outExt = DocExt.XLSX;
    }

    @Override
    public byte[] generateDoc(ArrayList fields, String templateName, boolean toBeZipped) throws IOException, ParseException {
        if (templateName.equals("GestioneSalariOspedale.xlsx")) {
            return util.insertTableFields(HospitalEmployee.getEmployees(fields), templateName,
                    Utils.getFileExtension(templateName), this.outExt, toBeZipped);
        } else if (templateName.equals("CovidToscana.xlsx")) {
            return util.insertTableFields(TuscanyProvince.getProvinces(fields), templateName,
                    Utils.getFileExtension(templateName), this.outExt, toBeZipped);
        } else {
            return null;
        }

    }

}
