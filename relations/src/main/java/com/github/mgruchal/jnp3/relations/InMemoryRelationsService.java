package com.github.mgruchal.jnp3.relations;

import com.github.mgruchal.jnp3.relations.users.User;
import com.github.mgruchal.jnp3.relations.users.UsersService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class InMemoryRelationsService implements RelationsService {
    private final Map<UUID, Set<UUID>> relations = new HashMap<>();

    @Inject
    @RestClient
    UsersService users;

    @Override
    public Set<User> findFollowees(UUID user, boolean withUsernames) throws UserNotFoundException {
        if (!userExists(user)) {
            throw new UserNotFoundException();
        }

        return relations
                .computeIfAbsent(user, k -> new HashSet<>())
                .stream()
                .map(id -> withUsernames ? users.getOne(id.toString()) : new User(id.toString()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UUID> findFollowers(UUID user) {
        return null;
    }

    @Override
    public void follow(UUID follower, UUID followee) {
        relations
                .computeIfAbsent(follower, k -> new HashSet<>())
                .add(followee);
    }

    @Override
    public void unfollow(UUID follower, UUID followee) {
        if (relations.containsKey(follower)) {
            relations.get(follower).remove(followee);
        }
    }

    private boolean userExists(UUID id) {
        var user = users.getOne(id.toString());
        return user != null;
    }

    static class UserNotFoundException extends Exception {
    }
}
