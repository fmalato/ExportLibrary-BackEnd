package businessLogic;

import exportLibrary.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateDoc(JSONObject jObj) {
        Response.ResponseBuilder rb = Response.ok(Controller.generateDocument(
                                                    Utils.getFileExtension(jObj.get("metadata").toString()),
                                                    (ArrayList)jObj.get("data"),
                                                    jObj.get("metadata").toString()
                                                    ));
        rb.header("Access-Control-Allow-Origin", "http://localhost:4200/#/form-template");
        rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        rb.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return rb.build();
    }


}

