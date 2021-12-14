package org.leafnode.jnp3.service;

import org.leafnode.jnp3.model.Order;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Default
public class InMemoryOrderService implements OrderService
{
    List<Order> orders;

    @Inject
    OrderProjector projector;

    InMemoryOrderService()
    {
        this.orders = new ArrayList<>();
    }

    @Override
    public List<Order> findOrders(String query) {
        return this.orders.stream().filter(order -> order.getId().toString().contains(query)).collect(Collectors.toList());
    }

    public void addOrder(Order order) throws IOException
    {
        orders.add(order);
        projector.add(order);
    }
}
