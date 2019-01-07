package picshare.userservice.rest.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import picshare.userservice.entitete.jpa.User;
import picshare.userservice.storitve.beans.UporabnikBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Context
    protected UriInfo uriInfo;

    @Inject
    UporabnikBean uB;

    @GET
    public Response returnUsers(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<User> users = uB.getAllUsers(query);
        if(users != null && users.size() > 0) {
            return Response.status(Response.Status.OK).entity(users).build();
        }
        else {
            throw new NotFoundException();
        }
    }

    @Path("{id}")
    @GET
    public Response returnUser(@PathParam("id") Integer id){
        User user = uB.getUser(id);
        if(user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    public Response addUser(User user){
        User u = uB.addUser(user);
        return Response.status(Response.Status.OK).entity(u).build();
    }

    @Path("{id}")
    @POST
    public Response updateUser(@PathParam("id") Integer id, User user) {
        uB.updateUser(id, user);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteUser(@PathParam("id") Integer id) {
        try {
            uB.deleteUser(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
