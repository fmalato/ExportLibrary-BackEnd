package businessLogic;

import exportLibrary.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;


@Path("/")
public class RestEndPoint {

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        Response.ResponseBuilder rb = Response.ok(Controller.getDBCategories());
        rb.header("Access-Control-Allow-Origin", "*");
        rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        rb.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return rb.build();
    }

    @GET
    @Path("/form/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFormCategory(@PathParam("category") String category) {
        Response.ResponseBuilder rb = Response.ok(Controller.getDBFormCategory(category));
        rb.header("Access-Control-Allow-Origin", "*");
        rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        rb.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return rb.build();
    }

    @GET
    @Path("/templates/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemplateNameCategory(@PathParam("category") String category) {
        Response.ResponseBuilder rb = Response.ok(Controller.getDBTemplateNameCategory(category));
        rb.header("Access-Control-Allow-Origin", "*");
        rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        rb.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return rb.build();
    }


    @POST
    @Path("/form/{category}/export")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateDoc(JSONObject jObj) {
        byte[] entity = Controller.generateDocument(
                            Utils.getFileExtension(jObj.get("metadata").toString()),
                            (ArrayList)jObj.get("data"),
                            jObj.get("metadata").toString());
        JSONObject jObjResponse = new JSONObject();
        jObjResponse.put("response", entity);

        Response.ResponseBuilder rb = Response.ok(jObjResponse, MediaType.APPLICATION_JSON);
        rb.header("Access-Control-Allow-Origin", "*");
        rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        rb.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return rb.build();
    }

}

