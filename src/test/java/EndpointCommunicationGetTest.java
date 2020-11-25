import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;


public class EndpointCommunicationGetTest {

    private static RequestSpecification baseSpec;
    private static RequestSpecification formSpec;
    private static RequestSpecification templateSpec;

    @BeforeClass
    public static void initSpec() {
        baseSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/ExportLibrary-BackEnd-1.0-SNAPSHOT/")
                .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                .addFilter(new RequestLoggingFilter())
                .build();

        formSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/ExportLibrary-BackEnd-1.0-SNAPSHOT/form/")
                .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                .addFilter(new RequestLoggingFilter())
                .build();

        templateSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/ExportLibrary-BackEnd-1.0-SNAPSHOT/templates/")
                .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void getCategoriesTest() {
        HashSet<String> set = new HashSet<>();
        set.add("Curriculum");
        set.add("Certificato di Malattia");
        set.add("COVID Toscana");
        set.add("Salari Ospedale");
        JSONArray categories = given().spec(baseSpec)
                .when()
                .get("categories")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);
        HashSet<String> extractedSet = new HashSet<>();
        Iterator jsonArrayItr = categories.iterator();

        while(jsonArrayItr.hasNext()) {
            String currentObj = (String)jsonArrayItr.next();
            extractedSet.add(currentObj);
        }

        assert extractedSet.equals(set);
    }

    @Test
    public void getFormCategoryCurriculumTest() {
        HashSet<String> set = new HashSet<>();
        set.add("firstname");
        set.add("lastname");
        set.add("dateofBirth");
        set.add("address");
        set.add("phone");
        set.add("mail");
        set.add("list");
        set.add("image");
        JSONArray form = given().spec(formSpec)
                .when()
                .get("Curriculum")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);
        HashSet<String> extractedSet = new HashSet<>();
        Iterator jsonArrayItr = form.iterator();

        while(jsonArrayItr.hasNext()) {
            LinkedHashMap<String, String> currentObj = (LinkedHashMap<String, String>)jsonArrayItr.next();
            extractedSet.add(currentObj.get("label"));
        }
        assert extractedSet.equals(set);

    }

    @Test
    public void getFormCategoryCertificatoDiMalattiaTest() {
        HashSet<String> set = new HashSet<>();
        set.add("doctor");
        set.add("doctorAddress");
        set.add("doctorPhone");
        set.add("patientFirstname");
        set.add("patientLastname");
        set.add("patientDateofBirth");
        set.add("patientBirthPlace");
        set.add("sickness");
        set.add("beginningDate");
        set.add("endingDate");
        JSONArray form = given().spec(formSpec)
                .when()
                .get("Certificato di Malattia")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);
        HashSet<String> extractedSet = new HashSet<>();
        Iterator jsonArrayItr = form.iterator();

        while(jsonArrayItr.hasNext()) {
            LinkedHashMap<String, String> currentObj = (LinkedHashMap<String, String>)jsonArrayItr.next();
            extractedSet.add(currentObj.get("label"));
        }
        assert extractedSet.equals(set);

    }

    @Test
    public void getFormCategoryGestioneSalariOspedaleTest() {
        HashSet<String> set = new HashSet<>();
        set.add("firstname");
        set.add("lastname");
        set.add("job");
        set.add("salary");
        set.add("extraHours");
        JSONArray form = given().spec(formSpec)
                .when()
                .get("Salari Ospedale")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);
        HashSet<String> extractedSet = new HashSet<>();
        Iterator jsonArrayItr = form.iterator();

        while(jsonArrayItr.hasNext()) {
            LinkedHashMap<String, String> currentObj = (LinkedHashMap<String, String>)jsonArrayItr.next();
            extractedSet.add(currentObj.get("label"));
        }
        assert extractedSet.equals(set);

    }

    @Test
    public void getFormCategoryCovidToscanaTest() {
        HashSet<String> set = new HashSet<>();
        set.add("province");
        set.add("dailyCases");
        set.add("globalCases");
        set.add("recovered");
        set.add("deaths");
        set.add("intensiveCares");
        JSONArray form = given().spec(formSpec)
                .when()
                .get("Covid Toscana")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);
        HashSet<String> extractedSet = new HashSet<>();
        // this is needed since Covid Toscana has a fixed number of elements inside the JSONArray
        ArrayList firstProvince = (ArrayList)form.get(0);
        Iterator jsonArrayItr = firstProvince.iterator();

        while(jsonArrayItr.hasNext()) {
            LinkedHashMap<String, String> currentObj = (LinkedHashMap<String, String>)jsonArrayItr.next();
            extractedSet.add(currentObj.get("label"));
        }
        assert extractedSet.equals(set);

    }

    @Test
    public void getTemplateNameCategoryTest() {
        HashSet<String> set = new HashSet<>();
        set.add("CurriculumEuropeo.docx");
        set.add("CurriculumModelloLondra.docx");
        JSONArray templates = given().spec(templateSpec)
                .when()
                .get("Curriculum")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);
        HashSet<String> extractedSet = new HashSet<>();
        Iterator jsonArrayItr = templates.iterator();

        while(jsonArrayItr.hasNext()) {
            String currentObj = (String)jsonArrayItr.next();
            extractedSet.add(currentObj);
        }
        assert extractedSet.equals(set);
    }

    @Test
    public void getTemplateNameSicknessTest() {
        JSONArray templates = given().spec(templateSpec)
                .when()
                .get("Certificato di Malattia")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);

        assert templates.get(0).equals("CertificatoMalattia.docx");
    }

    @Test
    public void getTemplateNameCovidTest() {
        JSONArray templates = given().spec(templateSpec)
                .when()
                .get("Covid Toscana")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);

        assert templates.get(0).equals("CovidToscana.xlsx");
    }

    @Test
    public void getTemplateNameSalariesTest() {
        JSONArray templates = given().spec(templateSpec)
                .when()
                .get("Salari Ospedale")
                .then()
                .statusCode(200)
                .extract()
                .as(JSONArray.class);

        assert templates.get(0).equals("GestioneSalariOspedale.xlsx");
    }

}
