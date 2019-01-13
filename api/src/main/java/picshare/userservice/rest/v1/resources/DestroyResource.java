package picshare.userservice.rest.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import org.apache.log4j.LogManager;
import picshare.userservice.storitve.beans.DummyBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("destroy")
@Log
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DestroyResource {
    private static final org.apache.log4j.Logger LOG = LogManager.getLogger(DestroyResource.class.getName());

    @Inject
    DummyBean dB;

    @GET
    @Produces("text/html")
    public Response destroy(){
        dB.setHealty(false);
        return Response.status(Response.Status.OK).entity("DESTROY!!!").build();
    }
}
