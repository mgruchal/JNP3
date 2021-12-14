package org.leafnode.jnp3.model;

public class LineItem {
    int order;
    Product product;
    int quantity;
    Money price;

    public int getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public LineItem(int order, Product product, int quantity, Money price)
    {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

}
