package org.leafnode.jnp3.service;

import org.leafnode.jnp3.model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    List<Order> findOrders(String query);
    void addOrder(Order order) throws IOException;
}
