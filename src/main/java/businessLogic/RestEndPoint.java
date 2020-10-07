package businessLogic;

import exportLibrary.DocExt;
import exportLibrary.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
    @Path("/form/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateDoc(@PathParam("category") String category, JSONObject jObj) {
        Response.ResponseBuilder rb = Response.ok(Controller.generateDocument(
                                                    Utils.getFileExtension(jObj.get("name").toString()),
                                                    (JSONArray)jObj.get("form"),
                                                    category
                                                    ));
        rb.header("Access-Control-Allow-Origin", "*");
        rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        rb.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
        return rb.build();
    }


}

