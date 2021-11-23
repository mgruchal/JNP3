package com.github.mgruchal.jnp3.relations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class FollowRequest {
    public String id;

    public FollowRequest() {}
    public FollowRequest(String id) {
        this.id = id;
    }
}
