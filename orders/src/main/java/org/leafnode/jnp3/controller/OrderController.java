package org.leafnode.jnp3.controller;

import org.leafnode.jnp3.model.*;
import org.leafnode.jnp3.service.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Path("/order")
public class OrderController {
    @Context
    HttpHeaders headers;

    @Context
    ResourceInfo resourceInfo;

    @Inject
    InMemoryOrderService ordersRepository;

    @Inject
    ESOrderProjector ordersProjector;

    @Inject
    CachedUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response get(@PathParam("id") String query) {
        try {
            var orders = ordersRepository.findOrders(query);
            return Response.ok(orders).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    public Response query(@QueryParam("query") String query) {
        try {
            var orders = ordersProjector.search(query);
            return Response.ok(orders).build();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return Response
                    .serverError()
                    .entity(Map.of(
                            "requestHeaders", headers.getRequestHeaders(),
                            "endpointClass", resourceInfo.getResourceClass(),
                            "endpointMethod", resourceInfo.getResourceMethod().getName(),
                            "stacktrace", sw.toString()))
                    .build();

        }
    }

    @GET
    @Path("/addFixtures")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addFixtures() {
        Product product1 = new Product("Soap");
        Product product2 = new Product("Toothpaste");
        Product product3 = new Product("Shampoo");
        Product product4 = new Product("Washing Soap");
        User user1;
        User user2;
        User user3;

//        User user1 = new User(UUID.randomUUID(), "soapy", "John Soap", "soap@gmail.com");
//        User user2 = new User(UUID.randomUUID(), "unknown", "James Doe", "jdoe@hotmail.com");
//        User user3 = new User(UUID.randomUUID(), "chris", "Chris Pratt", "chris.pratt@hollywood.com");
        try {
            user1 = userService.getUser("soapy");
            user2 = userService.getUser("unknown");
            user3 = userService.getUser("chris");
        } catch (UserNotFound e) {
            return Response.serverError().status(500).build();
        }

        try {
            this.ordersRepository.addOrder(
                    new Order(
                            UUID.randomUUID(),
                            user1,
                            new ArrayList<>(Arrays.asList(
                                    new LineItem(1, product1, 1, new Money(1000)),
                                    new LineItem(2, product3, 2, new Money(800))
                            ))
                    )
            );
            this.ordersRepository.addOrder(
                    new Order(
                            UUID.randomUUID(),
                            user2,
                            new ArrayList<>(Arrays.asList(
                                    new LineItem(1, product2, 1, new Money(700))
                            ))
                    )
            );
            this.ordersRepository.addOrder(
                    new Order(
                            UUID.randomUUID(),
                            user3,
                            new ArrayList<>(Arrays.asList(
                                    new LineItem(1, product3, 1, new Money(800))
                            ))
                    )
            );
            this.ordersRepository.addOrder(
                    new Order(
                            UUID.randomUUID(),
                            user2,
                            new ArrayList<>(Arrays.asList(
                                    new LineItem(1, product1, 2, new Money(900)),
                                    new LineItem(2, product3, 1, new Money(800))
                            ))
                    )
            );
            this.ordersRepository.addOrder(
                    new Order(
                            UUID.randomUUID(),
                            user1,
                            new ArrayList<>(Arrays.asList(
                                    new LineItem(2, product1, 1, new Money(800)),
                                    new LineItem(1, product4, 2, new Money(900))
                            ))
                    )
            );
            this.ordersRepository.addOrder(
                    new Order(
                            UUID.randomUUID(),
                            user1,
                            new ArrayList<>(Arrays.asList(
                                    new LineItem(1, product2, 1, new Money(800))
                            ))
                    )
            );
            return Response.status(Response.Status.OK).build();
        } catch (IOException e) {
            return Response.serverError().status(500).build();
        }
    }
}