package org.leafnode.jnp3.service;

import org.leafnode.jnp3.model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderProjector {
    void add(Order order) throws IOException;
    List<Order> search(String query) throws IOException;
}
