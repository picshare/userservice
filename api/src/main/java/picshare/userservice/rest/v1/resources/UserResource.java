package picshare.userservice.rest.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.apache.log4j.LogManager;
import org.eclipse.microprofile.metrics.annotation.Metered;
import picshare.userservice.entitete.jpa.User;
import picshare.userservice.storitve.beans.DummyBean;
import picshare.userservice.storitve.beans.UporabnikBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("user")
@Log
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {
    private static final org.apache.log4j.Logger LOG = LogManager.getLogger(UserResource.class.getName());


    @Context
    protected UriInfo uriInfo;

    @Inject
    UporabnikBean uB;

    @Inject
    DummyBean dB;

    @GET
    @Metered(name = "requests.get.users")
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
    @Metered(name = "requests.get.user")
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
    @Metered(name = "requests.add.users")
    public Response addUser(User user){
        User u = uB.addUser(user);
        return Response.status(Response.Status.OK).entity(u).build();
    }

    @Path("{id}")
    @POST
    @Metered(name = "requests.update.users")
    public Response updateUser(@PathParam("id") Integer id, User user) {
        uB.updateUser(id, user);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @Path("{id}")
    @DELETE
    @Metered(name = "requests.delete.users")
    public Response deleteUser(@PathParam("id") Integer id) {
        try {
            uB.deleteUser(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
