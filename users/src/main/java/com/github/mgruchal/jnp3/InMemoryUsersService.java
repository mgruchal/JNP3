package com.github.mgruchal.jnp3;

import com.github.mgruchal.jnp3.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@Default
public class InMemoryUsersService implements UsersService {

    private final Set<User> users = Collections.synchronizedSet(new LinkedHashSet<>());

    @Override
    public User find(UUID id) throws UserNotFound {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(UserNotFound::new);
    }

    @Override
    public void save(User user) throws UserAlreadyExists {
        if (users.stream().anyMatch(existingUser -> existingUser.getId().equals(user.getId()) || existingUser.getUsername().equals(user.getUsername()) || existingUser.getEmail().equals(user.getEmail()))) {
            throw new UserAlreadyExists();
        }
        users.add(user);
    }

    @Override
    public Set<User> getAll() {
        return users;
    }
}