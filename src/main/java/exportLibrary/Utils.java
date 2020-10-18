package exportLibrary;

import formModels.CurriculumForm;
import formModels.Form;

import formModels.HospitalEmployee;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Utils {

    private final DocExt[] supportedExts;
    private final String absolutePath = "/Users/francescogradi/Desktop/ExportLibrary-BackEnd/";

    public Utils() {
        this.supportedExts = DocExt.values();
    }

    /**
     * Replaces fields values in the template
     * @param fieldValues A Form containing the template fields values
     * @param docName the name of the template
     * @param inExt the input extension of the file
     * @param outExt A DocExt containing the output file extension
     */
    public byte[] insertFields(ArrayList fieldValues, String docName, DocExt inExt, DocExt outExt, boolean toBeZipped) {

        if(Arrays.asList(this.supportedExts).contains(inExt)) {
            try {
                InputStream in = new BufferedInputStream(new FileInputStream( absolutePath + "templates/" + docName));
                IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
                IContext context = report.createContext();

                String key = "";
                String value = "";

                for (Object fieldValue : fieldValues) {

                    key = ((LinkedHashMap)fieldValue).get("label").toString();
                    if (!key.equals("list") && !key.equals("image")) {
                        try {
                            value = ((LinkedHashMap)fieldValue).get("value").toString();
                            context.put(key, value);
                        } catch (Exception e) {
                            continue;
                        }

                    } else if (key.equals("list")) {
                        List<String> list = new ArrayList<String>();
                        for (Object item : (ArrayList)((LinkedHashMap)fieldValue).get("value")) {
                            list.add(((LinkedHashMap)item).get("item").toString());
                        }
                        context.put(key, list);

                    } else if (key.equals("image")) {

                        String base64Image = "";
                        try {
                            base64Image = ((LinkedHashMap)fieldValue).get("value").toString().split(",")[1];
                        } catch (Exception e) {
                            continue;
                        }

                        FieldsMetadata metadata = new FieldsMetadata();
                        metadata.addFieldAsImage("image");
                        report.setFieldsMetadata(metadata);
                        File fileImage = new File(absolutePath + "tmp.jpg");

                        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
                        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                        ImageIO.write(img, "jpg", fileImage);

                        IImageProvider image = new FileImageProvider(fileImage);
                        image.setResize( true );
                        context.put("image", image);
                    }
                }

                File outFile = new File(absolutePath + "out_" + docName );
                OutputStream out = new FileOutputStream(outFile);
                report.process(context, out);
                if(toBeZipped) {
                    return zipFile(outFile.getPath());
                }

                return Files.readAllBytes(outFile.toPath());

            } catch (IOException | XDocReportException e) {
                e.printStackTrace();
            }
        }
        else {
            // This should throw something like "FileNotSupportedException"
        }
        return null;
    }

    /**
     * Simple utility method to get the file extension from its name
     * @param fileName the name of the file
     * @return DocExt the extension as an enumeration
     */
    public static DocExt getFileExtension(String fileName) {

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

    /**
     * Works as a mapper from Json to Java class using the JSON Simple API methods
     * @return Form a compiled Form corresponding to the given Json String
     */
    public Form mapJsonToObject(JSONArray jArray) {
        // TODO: generalize to other Forms
        Form cf = new CurriculumForm("tmp", "Curriculum");
        Field[] fields = cf.getClass().getDeclaredFields();
        Iterator jsonArrayItr = jArray.iterator();

        while(jsonArrayItr.hasNext()) {
            for(Field f : fields) {
                JSONObject nextObj = (JSONObject)jsonArrayItr.next();
                if(nextObj.get("label").equals(f.getName())) {
                    try {
                        Field currentField = cf.getClass().getDeclaredField(f.getName());
                        // TODO: look for a better solution
                        currentField.setAccessible(true);
                        currentField.set(cf, nextObj.get("value"));
                        currentField.setAccessible(false);
                    } catch(NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cf;
    }

    public byte[] insertTableFields(List<HospitalEmployee> employees, String docName, DocExt fileExtension, DocExt outExt, boolean toBeZipped) {

        try(InputStream is = new BufferedInputStream(new FileInputStream( absolutePath + "templates/" + docName));) {
            Context context = new Context();
            context.putVar("employees", employees);

            File outFile = new File(absolutePath + "templates/out_" + docName );
            OutputStream os = new FileOutputStream(outFile);

            JxlsHelper.getInstance().processTemplate(is, os, context);

            if(toBeZipped) {
                return zipFile(outFile.getPath());
            }

            return Files.readAllBytes(outFile.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static byte[] zipFile(String filePath) {
        try {
            File file = new File(filePath);
            String zipFileName = file.getPath().concat(".zip");

            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry(file.getName()));

            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
            zos.close();

            file = new File(zipFileName);
            return Files.readAllBytes(file.toPath());

        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", filePath);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
        return null;
    }

}
