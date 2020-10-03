package businessLogic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/exportendpoint")
public class RestEndPoint {

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() { return Response
            .status(Response.Status.OK)
            .entity(Controller.getDBCategories())
            .build(); }

    @GET
    @Path("/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFormCategory(@PathParam("category") String category) { return Response
            .status(Response.Status.OK)
            .entity(Controller.getDBFormCategory(category))
            .build(); }

}

