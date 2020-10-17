package businessLogic;

import exportLibrary.Utils;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
                            jObj.get("metadata").toString(),
                            (boolean)jObj.get("zip"));
        HashMap<String, String> hm = new HashMap<>();
        hm.put("response", new String(entity));
        JSONObject jObjResponse = new JSONObject(hm);
        Response.ResponseBuilder rb = Response.ok(jObjResponse, MediaType.APPLICATION_JSON);
        rb.header("Access-Control-Allow-Origin", "*");
        rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        rb.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return rb.build();
    }

}

