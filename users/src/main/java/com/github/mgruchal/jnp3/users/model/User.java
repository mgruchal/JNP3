package com.github.mgruchal.jnp3.users.model;

import java.util.UUID;

public class User {
    String username;
    String email;
    UUID id;

    public User(String email, String username) {
        id = UUID.randomUUID();
        this.email = email;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }
}
