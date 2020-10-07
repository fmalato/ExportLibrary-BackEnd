package businessLogic;

import exportLibrary.DocExt;
import org.json.simple.JSONArray;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class RestEndPoint {

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() { return Response
            .status(Response.Status.OK)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
            .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
            .entity(Controller.getDBCategories()) // breakpoint here
            .build(); }

    @GET
    @Path("/form/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFormCategory(@PathParam("category") String category) { return Response
            .status(Response.Status.OK)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
            .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
            .entity(Controller.getDBFormCategory(category))
            .build(); }

    @GET
    @Path("/templates/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemplateNameCategory(@PathParam("category") String category) { return Response
            .status(Response.Status.OK)
            .header("Access-Control-Allow-Origin", "*")
            .entity(Controller.getDBTemplateNameCategory(category))
            .build(); }


    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateDoc(DocExt ext, String fileName, String templateName) { return Response
            .status(Response.Status.OK)
            .entity(Controller.generateDocument(ext, fileName, templateName))
            .buid(); }
    }
    @Consumes
    */

}

