package org.leafnode.jnp3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(@JsonProperty("name") String name)
    {
        this.name = name;
    }
}
