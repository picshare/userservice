package picshare.userservice.rest.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import io.swagger.oas.annotations.enums.ParameterIn;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
import picshare.userservice.entitete.jpa.User;
import picshare.userservice.entitete.business.Error;
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

    @Operation(
            description = "Get all users",
            tags = "user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User",
                            content = @Content(schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No matching users avaliable",
                            content = @Content(schema = @Schema(implementation = Error.class))
                    )
            },
            parameters = {
                    @Parameter(name = "offset", description = "Offset from begining",in = ParameterIn.QUERY),
                    @Parameter(name = "limit", description = "Limit the number of returned users", in = ParameterIn.QUERY),
                    @Parameter(name = "order", description = "Order the returned users", in = ParameterIn.QUERY)
            }
    )
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

    @Operation(
            description = "Get user by Id",
            tags = "user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User",
                            content = @Content(schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No user with given id",
                            content = @Content(schema = @Schema(implementation = Error.class))
                    )
            }
    )
    @Path("{id}")
    @GET
    public Response returnUser(@Parameter(description = "User Id", required = true) @PathParam("id") Integer id){
        User user = uB.getUser(id);
        if(user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        }
        else {
            throw new NotFoundException();
        }

    }

    @Operation(
            description = "Adds a new user",
            tags = "user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User",
                            content = @Content(schema = @Schema(implementation = User.class))
                    )
            }
    )
    @POST
    public Response addUser(@Parameter(description = "Object representing an user (" +
            "\"name\": String , \"surname\": String, \"username\": String, \"email\": String)", required = true) User user){
        User u = uB.addUser(user);
        return Response.status(Response.Status.OK).entity(u).build();
    }

    @Operation(
            description = "Update an user",
            tags = "user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User",
                            content = @Content(schema = @Schema(implementation = User.class))
                    )
            }
    )
    @Path("{id}")
    @POST
    public Response updateUser(@Parameter(description = "User Id") @PathParam("id") Integer id, @Parameter(description = "New state of user (" +
            "\"name\": String, \"surname\": String, \"username\": String, \"email\": String)") User user) {
        uB.updateUser(id, user);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @Operation(
            description = "Delete user by Id",
            tags = "user",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "/",
                            content = @Content(schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content(schema = @Schema(implementation = Error.class))
                    )
            }
    )
    @Path("{id}")
    @DELETE
    public Response deleteUser(@Parameter(description = "User Id", required = true)@PathParam("id") Integer id) {
        try {
            uB.deleteUser(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
