package org.leafnode.jnp3.model;

import java.util.List;
import java.util.UUID;

public class Order {
    UUID id;
    User user;
    List<LineItem> productList;

    public Order(UUID id, User user, List<LineItem> productList) {
        this.id = id;
        this.user = user;
        this.productList = productList;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<LineItem> getProductList() {
        return productList;
    }
}
