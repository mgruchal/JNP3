package com.github.mgruchal.jnp3.users;

import com.github.mgruchal.jnp3.users.model.User;

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
        if (users.stream().anyMatch(existingUser -> existingUser.getId().equals(user.getId()) || existingUser.getEmail().equals(user.getEmail()))) {
            throw new UserAlreadyExists();
        }
        users.add(user);
    }

    @Override
    public User get(String username) throws UserNotFound {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElseThrow(UserNotFound::new);
    }

    @Override
    public Set<User> getAll() {
        return users;
    }
}
