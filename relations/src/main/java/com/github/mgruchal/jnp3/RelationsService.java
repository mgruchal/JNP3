package com.github.mgruchal.jnp3;

import com.github.mgruchal.jnp3.model.Relation;

import java.util.Set;
import java.util.UUID;

public interface RelationsService {
    Set<Relation> findFollowees(UUID user);
    Set<Relation> findFollowers(UUID user);
    void follow(UUID follower, UUID followee);
    void unfollow(UUID follower, UUID followee);
}
