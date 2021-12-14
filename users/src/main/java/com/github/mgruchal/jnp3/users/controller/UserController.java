package com.github.mgruchal.jnp3.users.controller;

import com.github.mgruchal.jnp3.users.UsersService;
import com.github.mgruchal.jnp3.users.model.User;
import io.quarkus.logging.Log;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UsersService users;

    @GET
    public Set<User> list() {
        return users.getAll();
    }

    @GET
    @Path("/get/{id}")
    public Response getOne(@PathParam("id") UUID id) {
        Log.info("requested user id=" + id.toString());
        try {
            var user = users.find(id);

            return Response.ok(user).build();
        } catch (UsersService.UserNotFound userNotFound) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/{username}")
    public Response findByUsername(@PathParam("username") String username) {
        Log.info("requested username=" + username);
        try {
            var user = users.get(username);

            return Response.ok(user).build();
        } catch (UsersService.UserNotFound userNotFound) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        try {
            users.save(user);
        } catch (UsersService.UserAlreadyExists e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(user).build();
    }
}
