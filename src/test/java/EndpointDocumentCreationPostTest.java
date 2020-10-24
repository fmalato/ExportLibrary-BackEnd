import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.form;
import static io.restassured.RestAssured.given;

public class EndpointDocumentCreationPostTest {

    private static JSONObject formPayload;
    private static JSONObject notePayload;
    private static JSONObject tablePayload;

    private static RequestSpecification formSpec;

    @BeforeClass
    public static void initSpec() {
        formSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/ExportLibrary-BackEnd-1.0-SNAPSHOT/form/")
                .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                .addFilter(new RequestLoggingFilter())
                .build();

    }

    @BeforeClass
    public static void initPayloads() {
        // Initializing formPayload
        formPayload = new JSONObject();
        JSONArray dataFormPayload = new JSONArray();
        String[] formFieldNames = {"firstname", "lastname", "address", "phone", "mail", "dateofBirth", "list", "image"};
        String[] formFieldTypes = {"text", "text", "text", "text", "text", "date", "list", "image"};
        String[] formFieldValues = {"a", "b", "c", "1234", "e@f.com", "11/11/11", "[]", "image"};
        for(int i = 0; i < formFieldNames.length; i++) {
            JSONObject jObj = new JSONObject();
            jObj.put("label", formFieldNames[i]);
            jObj.put("type", formFieldTypes[i]);
            jObj.put("value", formFieldValues[i]);
            dataFormPayload.add(jObj);
        }
        formPayload.put("metadata", "CurriculumEuropeo.docx");
        formPayload.put("data", dataFormPayload);
        formPayload.put("zip", false);
        // Initializing notePayload
        notePayload = new JSONObject();
        JSONArray dataNotePayload = new JSONArray();
        String[] noteFieldNames = {"doctor", "doctorAddress", "doctorPhone", "patientFirstname", "patientLastname",
                                   "patientBirthPlace", "patientDateofBirth", "beginningDate", "endingDate",
                                   "sickness"};
        String[] noteFieldTypes = {"text", "text", "text", "text", "text", "text", "date", "date", "date", "text"};
        String[] noteFieldValues = {"a", "b", "c", "d", "e", "f", "11/11/11", "22/11/22", "22/11/33", "g"};
        for(int i = 0; i < noteFieldNames.length; i++) {
            JSONObject jObj = new JSONObject();
            jObj.put("label", noteFieldNames[i]);
            jObj.put("type", noteFieldTypes[i]);
            jObj.put("value", noteFieldValues[i]);
            dataNotePayload.add(jObj);
        }
        notePayload.put("metadata", "CertificatoMalattia.docx");
        notePayload.put("data", dataFormPayload);
        notePayload.put("zip", false);
        // Initializing tablePayload
        tablePayload = new JSONObject();
        JSONArray dataTablePayload = new JSONArray();
        String[] tableFieldNames = {"firstname", "lastname", "job", "salary", "extraHours"};
        String[] tableFieldTypes = {"text", "text", "text", "number", "number"};
        String[] tableFieldValues = {"a", "b", "c", "1234", "5678"};
        for(int i = 0; i < tableFieldNames.length; i++) {
            JSONObject jObj = new JSONObject();
            jObj.put("label", tableFieldNames[i]);
            jObj.put("type", tableFieldTypes[i]);
            jObj.put("value", tableFieldValues[i]);
            dataTablePayload.add(jObj);
        }
        tablePayload.put("metadata", "GestioneSalariOspedale.xlsx");
        tablePayload.put("data", dataFormPayload);
        tablePayload.put("zip", false);
    }

    @Test
    public void postFormPayload() {
         given()
                        .spec(formSpec)
                        .contentType(ContentType.JSON)
                        .body(formPayload)
                        .post("Curriculum/export")
                        .then()
                        .statusCode(200);
    }

    @Test
    public void postNotePayload() {
         given()
                .spec(formSpec)
                .contentType(ContentType.JSON)
                .body(formPayload)
                .post("Certificato%20di%20Malattia/export")
                .then()
                .statusCode(200);
    }

    @Test
    public void postTablePayload() {
         given()
                .spec(formSpec)
                .contentType(ContentType.JSON)
                .body(formPayload)
                .post("Salari%20Ospedale/export")
                .then()
                .statusCode(200);
    }

}
