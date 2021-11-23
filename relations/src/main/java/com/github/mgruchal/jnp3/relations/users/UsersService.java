package com.github.mgruchal.jnp3.relations.users;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/users")
@ApplicationScoped
@RegisterRestClient(configKey = "users-api")
public interface UsersService {
    @Path("/{id}")
    @GET
    User getOne(@PathParam("id") String id);
}
