package picshare.userservice.rest.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import io.swagger.oas.annotations.enums.ParameterIn;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
import picshare.userservice.entitete.business.Info;
import picshare.userservice.entitete.jpa.User;
import picshare.userservice.entitete.business.Error;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class InfoResource {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @GET
    public Response returnInfo(){
        Info info = new Info();

        List<String> clani = new ArrayList();
        clani.add("jc3731");
        info.setClani(clani);

        info.setOpis_projekta("Moj projekt implementira aplikacijo za deljenje slik.");

        List<String> mikrostoritve = new ArrayList();
        mikrostoritve.add("");
        info.setMikrostoritve(mikrostoritve);


        List<String> github = new ArrayList();
        github.add("https://github.com/picshare/userservice");
        github.add("https://github.com/picshare/storage");
        github.add("https://github.com/picshare/imagemanagement");
        github.add("https://github.com/picshare/commentsmanagement");
        github.add("https://github.com/picshare/frontend");
        info.setGithub(github);


        List<String> travis = new ArrayList();
        travis.add("https://travis-ci.com/picshare/userservice");
        travis.add("https://travis-ci.com/picshare/storage");
        travis.add("https://travis-ci.com/picshare/imagemanagement");
        travis.add("https://travis-ci.com/picshare/commentsmanagement");
        travis.add("https://travis-ci.com/picshare/frontend");
        info.setTravis(travis);

        List<String> dockerHub = new ArrayList();
        dockerHub.add("https://hub.docker.com/r/jernejcernelc/picshare-userservice/");
        dockerHub.add("https://hub.docker.com/r/jernejcernelc/picshare-storage/");
        info.setDockerhub(dockerHub);

        return Response.status(Response.Status.OK).entity(info).build();

    }
}
