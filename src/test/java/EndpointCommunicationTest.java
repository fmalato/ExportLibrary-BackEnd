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

public class EndpointCommunicationTest {

    private static RequestSpecification baseSpec;
    private static RequestSpecification formSpec;

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
    }

    @Test
    public void getCategoriesTest() {
        given().spec(baseSpec)
                .when()
                .get("categories")
                .then()
                .statusCode(200);
    }

    @Test
    public void getFormCategoryTest() {
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
    public void getTemplateNameCategoryTest() {
        given().spec(baseSpec)
                .when()
                .get("categories")
                .then()
                .statusCode(200);
    }

}
