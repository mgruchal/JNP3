package com.github.mgruchal.jnp3.relations;

import io.quarkus.logging.Log;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/users/{user}/followees")
@Produces(MediaType.APPLICATION_JSON)
public class RelationsController {

    @Inject
    RelationsService relations;

    @GET
    public Response findFollowees(@PathParam("user") UUID follower, @QueryParam("withUsernames") boolean withUsernames) {
        Log.info("foo");
        try {
            var followees = relations.findFollowees(follower, withUsernames);
            return Response.ok(followees).build();
        } catch (InMemoryRelationsService.UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response startFollowing(@PathParam("user") UUID follower, @NotNull FollowRequest request) {
        Log.info("bar");
        relations.follow(follower, UUID.fromString(request.id));
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response stopFollowing(@PathParam("user") UUID follower, @NotNull FollowRequest request) {
        Log.info("unsub from="+follower.toString());
        relations.unfollow(follower, UUID.fromString(request.id));
        return Response.accepted().build();
    }
}
