package com.github.mgruchal.jnp3.users.controller;
import  com.github.mgruchal.jnp3.users.model.Status;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusController {
    @GET
    public Response getStatus() {
        Status status = new Status();
        return Response.ok(status).build();
    }
}
