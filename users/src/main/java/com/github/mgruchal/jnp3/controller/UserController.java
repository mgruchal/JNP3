package com.github.mgruchal.jnp3.controller;

import com.github.mgruchal.jnp3.UsersService;
import com.github.mgruchal.jnp3.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    @GET
    public Set<User> execute() {
        return users.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        try {
            users.save(user);
        } catch (UsersService.UserAlreadyExists e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @Inject
    UsersService users;
}
