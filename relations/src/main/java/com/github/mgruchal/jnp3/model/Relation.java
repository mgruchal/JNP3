package com.github.mgruchal.jnp3.model;

import java.util.UUID;

public class Relation {
    UUID followee, follower;

    public Relation(UUID followee, UUID follower) {
        this.followee = followee;
        this.follower = follower;
    }
}
