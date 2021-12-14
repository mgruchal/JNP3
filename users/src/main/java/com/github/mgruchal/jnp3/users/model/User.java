package com.github.mgruchal.jnp3.users.model;

import java.util.UUID;

public class User {
    String username;
    String name;
    String email;
    UUID id;

    public User(String email, String username, String name) {
        id = UUID.randomUUID();
        this.email = email;
        this.username = username;
        this.name = name;
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

    public UUID getId() {
        return id;
    }
}
