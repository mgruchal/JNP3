package org.leafnode.jnp3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {
    UUID id;
    String username;
    String name;
    String email;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User(@JsonProperty("id") UUID id, @JsonProperty("username") String username, @JsonProperty("name") String name, @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }
}
