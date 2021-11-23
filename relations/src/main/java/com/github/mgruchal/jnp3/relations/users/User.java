package com.github.mgruchal.jnp3.relations.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String username;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String id) {
        this.id = id;
    }
}
