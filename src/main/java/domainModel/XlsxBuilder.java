package domainModel;

import exportLibrary.DocExt;
import exportLibrary.Utils;
import formModels.HospitalEmployee;

import org.json.simple.parser.ParseException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

        List<HospitalEmployee> employees = new ArrayList<>();
        for (Object element : fields) {
            try {
                employees.add(new HospitalEmployee(((LinkedHashMap) element).get("firstname").toString(),
                        ((LinkedHashMap) element).get("lastname").toString(),
                        ((LinkedHashMap) element).get("job").toString(),
                        (int) ((LinkedHashMap) element).get("salary"),
                        (int) ((LinkedHashMap) element).get("extraHours")));
            } catch (Exception e) { continue; }
        }

        return util.insertTableFields(employees, templateName, Utils.getFileExtension(templateName), this.outExt, toBeZipped);

    }

}
