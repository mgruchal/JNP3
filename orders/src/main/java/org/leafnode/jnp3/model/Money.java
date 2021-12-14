package org.leafnode.jnp3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Money
{
    int price;

    public Money(@JsonProperty("price") int price)
    {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    double asDouble() {
        return ((float)this.price)/100.0;
    }
}
