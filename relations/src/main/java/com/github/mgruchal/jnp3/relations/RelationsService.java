package com.github.mgruchal.jnp3.relations;

import com.github.mgruchal.jnp3.relations.users.User;

import java.util.Set;
import java.util.UUID;

public interface RelationsService {
    Set<User> findFollowees(UUID user, boolean withUsernames) throws InMemoryRelationsService.UserNotFoundException;
    Set<UUID> findFollowers(UUID user);
    void follow(UUID follower, UUID followee);
    void unfollow(UUID follower, UUID followee);
}
