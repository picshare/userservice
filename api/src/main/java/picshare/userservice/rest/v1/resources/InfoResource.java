package picshare.userservice.rest.v1.resources;

import picshare.userservice.entitete.business.Info;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        mikrostoritve.add("http://159.122.186.171:30001/v1/user");
        mikrostoritve.add("http://159.122.186.171:30002/v1/image");
        info.setMikrostoritve(mikrostoritve);


        List<String> github = new ArrayList();
        github.add("https://github.com/picshare/userservice");
        github.add("https://github.com/picshare/storage");
        github.add("https://github.com/picshare/imagemanagement");
        info.setGithub(github);


        List<String> travis = new ArrayList();
        travis.add("https://travis-ci.com/picshare/userservice");
        travis.add("https://travis-ci.com/picshare/storage");
        travis.add("https://travis-ci.com/picshare/imagemanagement");
        info.setTravis(travis);

        List<String> dockerHub = new ArrayList();
        dockerHub.add("https://hub.docker.com/r/jernejcernelc/picshare-userservice/");
        dockerHub.add("https://hub.docker.com/r/jernejcernelc/picshare-storage/");
        dockerHub.add("https://hub.docker.com/r/jernejcernelc/picshare-imagemanagement/");
        info.setDockerhub(dockerHub);

        return Response.status(Response.Status.OK).entity(info).build();

    }
}
